package tech.radhi.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;
import tech.radhi.portfolio.dto.CloudflareStats;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.dto.GithubStats;
import tech.radhi.portfolio.dto.UptimeStats;
import tech.radhi.portfolio.web.CloudflareService;
import tech.radhi.portfolio.web.GithubService;
import tech.radhi.portfolio.web.WebService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    @Mock
    private ContentService contentService;
    @Mock
    private CloudflareService cloudflareService;
    @Mock
    private GithubService githubService;
    @Mock
    private WebService webScraper;

    @InjectMocks
    private MainService mainService;

    private Model model;
    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        model = new ExtendedModelMap();
    }

    @Test
    void getIndexContent_shouldPopulateModelWithImages() {
        ContentTemplate imgContent = new ContentTemplate("img1", "img", "url1");
        when(contentService.getListOfContent("img")).thenReturn(Collections.singletonList(imgContent));

        mainService.getIndexContent(model);

        assertTrue(model.containsAttribute("imgs"));
        Map<String, String> imgs = (Map<String, String>) model.getAttribute("imgs");
        assertNotNull(imgs);
        assertEquals("url1", imgs.get("img1"));
        verify(contentService).getListOfContent("img");
    }

    @Test
    void handleHtmxRequests_shouldSetHeadlessTrue_whenHeaderIsTrue() {
        mainService.handleHtmxRequests(model, "true");
        assertTrue((Boolean) model.getAttribute("headless"));
    }

    @Test
    void handleHtmxRequests_shouldNotSetHeadless_whenHeaderIsNotTrue() {
        mainService.handleHtmxRequests(model, "false");
        assertNull(model.getAttribute("headless")); // Or assert it's false if it's defaulted
        mainService.handleHtmxRequests(model, null);
        assertNull(model.getAttribute("headless"));
    }


    @Test
    void getAboutContent_shouldPopulateModelWithSkillsAndProjects() {
        when(contentService.getContentById("skills")).thenReturn("fe1,fe2.be1,be2.to1,to2");
        when(contentService.getListOfContent("q")).thenReturn(Collections.emptyList());
        when(contentService.getListOfProjects()).thenReturn(Collections.emptyList());

        mainService.getAboutContent(model);

        assertTrue(model.containsAttribute("frontend"));
        assertTrue(model.containsAttribute("backend"));
        assertTrue(model.containsAttribute("tools"));
        assertTrue(model.containsAttribute("questions"));
        assertTrue(model.containsAttribute("projects"));

        assertEquals(List.of("fe1", "fe2"), model.getAttribute("frontend"));
        verify(contentService).getContentById("skills");
        verify(contentService).getListOfContent("q");
        verify(contentService).getListOfProjects();
    }
    @Test
    void getAboutContent_withLessThanThreeSkillParts_shouldUseErrorSkills() {
        when(contentService.getContentById("skills")).thenReturn("frontendSkillsOnly"); // Only one part
        when(contentService.getListOfContent("q")).thenReturn(Collections.emptyList());
        when(contentService.getListOfProjects()).thenReturn(Collections.emptyList());

        mainService.getAboutContent(model);

        assertEquals(List.of("sorry"), model.getAttribute("frontend"));
        assertEquals(List.of("error"), model.getAttribute("backend"));
        assertEquals(List.of("happened"), model.getAttribute("tools"));
    }


    @Test
    void getUptimeStats_shouldReturnFetchedStats() throws Exception {
        ArrayNode uptimeData = mapper.createArrayNode();
        uptimeData.add(mapper.createObjectNode()); // dummy 0 index
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.put("uptimeDay", "99.98%");
        dataNode.put("uptimeMonth", "99.95%");
        uptimeData.add(dataNode);

        when(webScraper.fetchJsonNode(anyString())).thenReturn(uptimeData);

        UptimeStats stats = mainService.getUptimeStats();

        assertEquals("99.98%", stats.uptimeDay());
        assertEquals("99.95%", stats.uptimeMonth());
        verify(webScraper).fetchJsonNode("https://raw.githubusercontent.com/bajahaw/web-monitor/master/history/summary.json");
    }

    @Test
    void getUptimeStats_whenFetcherThrowsException_shouldReturnDefault() throws Exception {
        when(webScraper.fetchJsonNode(anyString())).thenThrow(new RuntimeException("Fetch error"));

        UptimeStats stats = mainService.getUptimeStats();

        assertEquals("99%?", stats.uptimeDay());
        assertEquals("97%?", stats.uptimeMonth());
    }

    @Test
    void getCloudflareStats_shouldReturnFetchedStats() {
        ObjectNode siteData = mapper.createObjectNode();
        siteData.with("sum").put("requests", "12345");
        siteData.with("uniq").put("uniques", "678");

        // Mocking the fluent API of JsonNode
        JsonNode mockViewer = mock(JsonNode.class);
        JsonNode mockZones = mock(JsonNode.class);
        JsonNode mockTotals = mock(JsonNode.class);

        when(cloudflareService.getAnalytics()).thenReturn(mockViewer);
        when(mockViewer.path("viewer")).thenReturn(mockViewer);
        when(mockViewer.path("zones")).thenReturn(mockZones);
        when(mockZones.get(0)).thenReturn(mockZones);
        when(mockZones.path("totals")).thenReturn(mockTotals);
        when(mockTotals.get(0)).thenReturn(siteData);


        CloudflareStats stats = mainService.getCloudflareStats();

        assertEquals("12345", stats.totalRequests());
        assertEquals("678", stats.uniqueVisitors());
        verify(cloudflareService).getAnalytics();
    }

    @Test
    void getCloudflareStats_whenApiThrowsException_shouldReturnDefault() {
        when(cloudflareService.getAnalytics()).thenThrow(new RuntimeException("API error"));

        CloudflareStats stats = mainService.getCloudflareStats();

        assertEquals("1k?", stats.totalRequests());
        assertEquals("100?", stats.uniqueVisitors());
    }

    @Test
    void getGithubStats_shouldReturnFetchedStats() {
        ObjectNode githubData = mapper.createObjectNode();
        githubData.with("publicRepos").put("totalCount", 20);
        githubData.with("contributionsCollection").put("totalCommitContributions", 150);

        ArrayNode stargazersNodes = mapper.createArrayNode();
        stargazersNodes.add(mapper.createObjectNode().put("totalCount", 50));
        stargazersNodes.add(mapper.createObjectNode().put("totalCount", 75));
        // Simulate findValues behavior
        when(githubService.getAnalytics()).thenReturn(mapper.createObjectNode().set("user", githubData));
        // For the stream part, we need to mock the structure that findValues would iterate on
        // This is a simplification; a more complex JsonNode might require more elaborate mocking
        // or actually providing a JsonNode that `findValues("stargazers")` can process.
        // Let's assume the structure allows path("stargazers").path("totalCount") to work in a stream.
        // A more direct way if findValues is hard to mock:
        ObjectNode repo1 = mapper.createObjectNode();
        repo1.with("stargazers").put("totalCount", 50);
        ObjectNode repo2 = mapper.createObjectNode();
        repo2.with("stargazers").put("totalCount", 75);
        ArrayNode nodes = mapper.createArrayNode();
        nodes.add(repo1);
        nodes.add(repo2);
        githubData.set("repositories", mapper.createObjectNode().set("nodes", nodes)); // for findValues to find these

        // Re-mock getAnalytics to return the full structure for findValues
        JsonNode userNode = mock(JsonNode.class);
        JsonNode publicReposNode = mock(JsonNode.class);
        JsonNode totalCountPublicReposNode = mock(JsonNode.class);
        JsonNode contributionsNode = mock(JsonNode.class);
        JsonNode totalCommitNode = mock(JsonNode.class);
        JsonNode findValuesResultNode = mock(JsonNode.class); // This node would be what findValues returns elements of

        when(githubService.getAnalytics()).thenReturn(userNode);
        when(userNode.get("user")).thenReturn(userNode); // user(login) -> user field
        when(userNode.path("publicRepos")).thenReturn(publicReposNode);
        when(publicReposNode.path("totalCount")).thenReturn(totalCountPublicReposNode);
        when(totalCountPublicReposNode.asInt(0)).thenReturn(20);

        when(userNode.path("contributionsCollection")).thenReturn(contributionsNode);
        when(contributionsNode.path("totalCommitContributions")).thenReturn(totalCommitNode);
        when(totalCommitNode.asInt(0)).thenReturn(150);

        // Mocking for stars (this is tricky with findValues)
        // Let's assume a simpler structure for mocking or adjust MainService to make it more mockable
        // For this example, let's assume getAnalytics returns a JsonNode that, when findValues("stargazers") is called,
        // it returns a list of nodes, each having a totalCount.
        JsonNode stargazer1 = mapper.createObjectNode().put("totalCount", 50);
        JsonNode stargazer2 = mapper.createObjectNode().put("totalCount", 75);
        List<JsonNode> stargazersList = List.of(stargazer1, stargazer2);
        when(userNode.findValues("stargazers")).thenReturn(stargazersList);


        GithubStats stats = mainService.getGithubStats();

        assertEquals(20, stats.publicRepos());
        assertEquals(125, stats.stars()); // 50 + 75
        assertEquals(150, stats.recentCommits());
        verify(githubService).getAnalytics();
    }


    @Test
    void getGithubStats_whenApiThrowsException_shouldReturnDefault() {
        when(githubService.getAnalytics()).thenThrow(new RuntimeException("API error"));

        GithubStats stats = mainService.getGithubStats();

        assertEquals(0, stats.publicRepos());
        assertEquals(0, stats.stars());
        assertEquals(0, stats.recentCommits());
    }
}