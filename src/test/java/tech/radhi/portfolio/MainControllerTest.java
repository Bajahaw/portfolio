package tech.radhi.portfolio;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import gg.jte.springframework.boot.autoconfigure.JteViewResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import tech.radhi.portfolio.dto.CloudflareStats;
import tech.radhi.portfolio.dto.GithubStats;
import tech.radhi.portfolio.dto.UptimeStats;

import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MainService mainService;


    @BeforeEach
    void setup() {
        CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/jte")); // This is the directory where your .jte files are located.
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        JteViewResolver jteViewResolver = new JteViewResolver(templateEngine, ".jte");

        // Build the MockMvc with Jte resolver to avoid circular view
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MainController(mainService))
                .setViewResolvers(jteViewResolver)
                .build();
    }

    @Test
    void index_shouldReturnIndexViewAndPopulateModel() throws Exception {
        doNothing().when(mainService).getIndexContent(any(Model.class));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
        // .andExpect(model().attributeExists("headless"));

        verify(mainService).getIndexContent(any(Model.class));
    }

    @Test
    void about_shouldReturnAboutViewAndPopulateModel() throws Exception {
        doNothing().when(mainService).getAboutContent(any(Model.class));

        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void stats_shouldReturnStatsViewAndPopulateModel() throws Exception {
        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("stats"));
    }

    @Test
    void getGithubStats_shouldReturnFragmentViewAndPopulateModel() throws Exception {
        GithubStats mockStats = new GithubStats(10, 100, 50);
        when(mainService.getGithubStats()).thenReturn(mockStats);

        mockMvc.perform(get("/github-stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/github-stats"))
                .andExpect(model().attribute("stats", mockStats));

        verify(mainService).getGithubStats();
    }

    @Test
    void getCloudflareStats_shouldReturnFragmentViewAndPopulateModel() throws Exception {
        CloudflareStats mockStats = new CloudflareStats("1000", "100");
        when(mainService.getCloudflareStats()).thenReturn(mockStats);

        mockMvc.perform(get("/cloudflare-stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/request-stats"))
                .andExpect(model().attribute("stats", mockStats));

        verify(mainService).getCloudflareStats();
    }

    @Test
    void getUptimeStats_shouldReturnFragmentViewAndPopulateModel() throws Exception {
        UptimeStats mockStats = new UptimeStats("99.9%", "99.5%");
        when(mainService.getUptimeStats()).thenReturn(mockStats);

        mockMvc.perform(get("/upptime-stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/upptime-stats"))
                .andExpect(model().attribute("stats", mockStats));

        verify(mainService).getUptimeStats();
    }

    @Test
    void forbidden_shouldReturn403FragmentView() throws Exception {
        doNothing().when(mainService).handleHtmxRequests(any(Model.class), any());

        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/403"));

        verify(mainService).handleHtmxRequests(any(Model.class), eq(null));
    }

    @Test
    void social_shouldReturnErrorViewForPageUnderConstruction() throws Exception {
        doNothing().when(mainService).handleHtmxRequests(any(Model.class), any());

        mockMvc.perform(get("/soon"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("status", 503))
                .andExpect(model().attribute("error", "Page Under Construction"))
                .andExpect(model().attributeExists("timestamp"));
    }
}