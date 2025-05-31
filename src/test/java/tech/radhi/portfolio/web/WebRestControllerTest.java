package tech.radhi.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tech.radhi.portfolio.MainConfig;
import tech.radhi.portfolio.dto.Page;
import tech.radhi.portfolio.security.SecurityConfig;
import tech.radhi.portfolio.security.TokenFilter;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebRestController.class)
@Import({SecurityConfig.class, TokenFilter.class, MainConfig.class}) // Import necessary shared configs
@TestPropertySource(properties = {"token=test-secret-token", "url=[http://test.dev](http://test.dev)"})
class WebRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WebService webScraper;

    @MockitoBean
    private CloudflareService cloudflareService; // This bean is used by WebRestController

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void scrapePage_shouldReturnPageData() throws Exception {
        String urlToScrape = "http://example.com";
        Page mockPage = new Page(URI.create(urlToScrape), "Title", Collections.emptyList(), Collections.emptyList());
        when(webScraper.scrape(urlToScrape)).thenReturn(mockPage);

        mockMvc.perform(get("/scrape").param("url", urlToScrape))
                .andExpect(status().isOk());
    }

    @Test
    void scrapePage_whenScraperThrowsException_shouldReturnNullOrHandleError() throws Exception {
        String urlToScrape = "[http://example.com](http://example.com)";
        when(webScraper.scrape(urlToScrape)).thenThrow(new RuntimeException("Scraping failed"));

        mockMvc.perform(get("/scrape").param("url", urlToScrape))
                .andExpect(status().isOk()) // because the controller handles the exception and returns null
                .andExpect(content().string("")); // Null page serializes to empty string or handled by HttpMessageConverter
    }

    @Test
    void getAnalytics_shouldReturnCloudflareData() throws Exception {
        JsonNode mockAnalyticsData = objectMapper.createObjectNode().put("key", "value");
        when(cloudflareService.getAnalytics()).thenReturn(mockAnalyticsData);

        mockMvc.perform(get("/cloudflare"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("value"));
    }

    @Test
    void scrapePagesIn_shouldReturnCorrectMessage() throws Exception {
        String urlToScrape = "http://example.com";
        Page mockPage = new Page(URI.create(urlToScrape), "Title", Collections.emptyList(), List.of("http://link1.com"));
        when(webScraper.scrape(urlToScrape)).thenReturn(mockPage);
        when(webScraper.scrape(eq("http://link1.com"))).thenReturn(mockPage);

        mockMvc.perform(get("/scrapePagesIn").param("url", urlToScrape))
                .andExpect(status().isOk())
                .andExpect(content().string("Discovered 1 links, inspect server logs to see the progress of the scraping."));
    }
}