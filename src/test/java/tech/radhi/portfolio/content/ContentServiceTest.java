package tech.radhi.portfolio.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.dto.ProjectTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock
    private ContentRepository contentRepository;
    @Mock
    private JdbcAggregateTemplate jdbcTemplate;
    @Spy // Using Spy for ObjectMapper to use its real methods but allow verification if needed
    private ObjectMapper objectMapper = new ObjectMapper();


    @InjectMocks
    private ContentService contentService;

    private ContentTemplate sampleContent;
    private ProjectTemplate sampleProject;
    private String sampleProjectJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        sampleContent = new ContentTemplate("testId", "testType", "Test Body");
        sampleProject = new ProjectTemplate(2023, "Test Project", "Description", List.of("Java"), "img.url");
        sampleProjectJson = objectMapper.writeValueAsString(sampleProject);
    }

    @Test
    void getContentById_whenFound_shouldReturnContentBody() {
        when(contentRepository.getContentById("testId")).thenReturn(sampleContent);
        String body = contentService.getContentById("testId");
        assertEquals("Test Body", body);
        verify(contentRepository).getContentById("testId");
    }

    @Test
    void getContentById_whenNotFound_shouldReturnDefaultMessage() {
        when(contentRepository.getContentById("unknownId")).thenReturn(null);
        String body = contentService.getContentById("unknownId");
        assertEquals("Oh!, Something seems off, got nothing to say ..", body);
    }

    @Test
    void getListOfContent_whenFound_shouldReturnList() {
        when(contentRepository.getAllByType("testType")).thenReturn(Collections.singletonList(sampleContent));
        List<ContentTemplate> list = contentService.getListOfContent("testType");
        assertFalse(list.isEmpty());
        assertEquals(sampleContent, list.get(0));
    }

    @Test
    void getListOfContent_whenNotFound_shouldReturnErrorContent() {
        when(contentRepository.getAllByType("unknownType")).thenReturn(Collections.emptyList());
        List<ContentTemplate> list = contentService.getListOfContent("unknownType");
        assertEquals(1, list.size());
        assertEquals("error", list.get(0).id());
        assertEquals("Oh!, Something seems off, got nothing to show ..", list.get(0).contentBody());
    }

    @Test
    void getListOfProjects_shouldReturnSortedListOfProjects() {
        ContentTemplate projectContent1 = new ContentTemplate("proj1", "project",
                "{\"year\":2022,\"title\":\"Old Project\",\"description\":\"D\",\"technologies\":[],\"imageUrl\":\"\"}");
        ContentTemplate projectContent2 = new ContentTemplate("proj2", "project",
                "{\"year\":2023,\"title\":\"New Project\",\"description\":\"D\",\"technologies\":[],\"imageUrl\":\"\"}");


        when(contentRepository.getAllByType("project")).thenReturn(List.of(projectContent1, projectContent2));

        List<ProjectTemplate> projects = contentService.getListOfProjects();

        assertEquals(2, projects.size());
        assertEquals(2023, projects.get(0).year());
        assertEquals(2022, projects.get(1).year());
    }

    @Test
    void toProject_shouldConvertContentTemplateToProjectTemplate() throws JsonProcessingException {
        ContentTemplate ct = new ContentTemplate("p1", "project", sampleProjectJson);
        ProjectTemplate pt = contentService.toProject(ct);

        assertEquals(sampleProject.year(), pt.year());
        assertEquals(sampleProject.title(), pt.title());
        verify(objectMapper).readValue(sampleProjectJson, ProjectTemplate.class);
    }

    @Test
    void toProject_withInvalidJson_shouldReturnErrorProject() throws JsonProcessingException {
        ContentTemplate ct = new ContentTemplate("p-err", "project", "invalid json");
        ProjectTemplate pt = contentService.toProject(ct);
        assertEquals(0, pt.year());
        assertEquals("error", pt.title());
        assertEquals("error happened", pt.description());
    }


    @Test
    void saveContent_whenExists_shouldUpdate() {
        when(contentRepository.existsById(sampleContent.id())).thenReturn(true);
        contentService.saveContent(sampleContent);
        verify(contentRepository).save(sampleContent);
        verify(jdbcTemplate, never()).insert(any());
    }

    @Test
    void saveContent_whenNotExists_shouldInsert() {
        when(contentRepository.existsById(sampleContent.id())).thenReturn(false);
        contentService.saveContent(sampleContent);
        verify(jdbcTemplate).insert(sampleContent);
        verify(contentRepository, never()).save(any(ContentTemplate.class));
    }

    @Test
    void deleteContent_shouldCallRepositoryDelete() {
        doNothing().when(contentRepository).deleteById("testId");
        contentService.deleteContent("testId");
        verify(contentRepository).deleteById("testId");
    }

    @Test
    void getAllContent_shouldCallRepositoryGetAll() {
        when(contentRepository.getAllContent()).thenReturn(Collections.singletonList(sampleContent));
        List<ContentTemplate> list = contentService.getAllContent();
        assertFalse(list.isEmpty());
        verify(contentRepository).getAllContent();
    }
}