package tech.radhi.portfolio.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tech.radhi.portfolio.MainConfig;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.security.SecurityConfig;
import tech.radhi.portfolio.security.TokenFilter;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContentController.class)
@Import({SecurityConfig.class, TokenFilter.class, MainConfig.class})
@TestPropertySource(properties = {"token=test-secret-token", "url=[http://test.dev](http://test.dev)"})
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String VALID_TOKEN = "test-secret-token";

    @Test
    void invalidateCache_withoutToken_shouldReturn403() throws Exception {
        mockMvc.perform(get("/content/invalidate-cache"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/403"));
    }

    @Test
    void invalidateCache_withValidToken_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/content/invalidate-cache").param("token", VALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("Cache Invalidated!"));
        verify(contentService, never()).getAllContent();
    }


    @Test
    void content_shouldReturnContentById() throws Exception {
        String contentId = "about-me";
        String expectedContent = "This is about me.";
        when(contentService.getContentById(contentId)).thenReturn(expectedContent);

        mockMvc.perform(get("/content/{id}", contentId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));

        verify(contentService).getContentById(contentId);
    }

    @Test
    void save_withoutToken_shouldReturn403() throws Exception {
        ContentTemplate newContent = new ContentTemplate("testId", "testType", "testBody");
        mockMvc.perform(post("/content/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newContent)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/403"));
    }

    @Test
    void save_shouldAddContent_withValidToken() throws Exception {
        ContentTemplate newContent = new ContentTemplate("testSave", "type", "body");
        doNothing().when(contentService).saveContent(any(ContentTemplate.class));

        mockMvc.perform(post("/content/save")
                        .param("token", VALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newContent)))
                .andExpect(status().isOk())
                .andExpect(content().string("added!"));

        verify(contentService).saveContent(newContent);
    }


    @Test
    void deleteContent_withoutToken_shouldReturn403() throws Exception {
        String contentId = "to-delete";
        mockMvc.perform(delete("/content/delete/{id}", contentId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/403"));
    }

    @Test
    void deleteContent_withValidToken_shouldDeleteAndReturnOk() throws Exception {
        String contentId = "to-delete";
        doNothing().when(contentService).deleteContent(contentId);

        mockMvc.perform(delete("/content/delete/{id}", contentId).param("token", VALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted!"));

        verify(contentService).deleteContent(contentId);
    }

    @Test
    void getAllContent_shouldReturnListOfContent() throws Exception {
        List<ContentTemplate> contentList = Collections.singletonList(
                new ContentTemplate("id1", "type1", "body1")
        );
        when(contentService.getAllContent()).thenReturn(contentList);

        mockMvc.perform(get("/content/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("id1"));

        verify(contentService).getAllContent();
    }


    @Test
    void saveAll_shouldSaveAllContent() throws Exception {
        List<ContentTemplate> contentList = List.of(
                new ContentTemplate("id1", "type1", "body1"),
                new ContentTemplate("id2", "type2", "body2")
        );
        doNothing().when(contentService).saveContent(any(ContentTemplate.class));

        mockMvc.perform(post("/content/save-all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentList))
                        .param("token", VALID_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().string("all saved"));

        verify(contentService, times(2)).saveContent(any(ContentTemplate.class));
    }

    @Test
    void saveAll_withoutToken_shouldReturn403() throws Exception {
        List<ContentTemplate> contentList = List.of(
                new ContentTemplate("id1", "type1", "body1")
        );
        mockMvc.perform(post("/content/save-all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentList)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/403"));
    }
}