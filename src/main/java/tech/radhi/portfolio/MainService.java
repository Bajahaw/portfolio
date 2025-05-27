package tech.radhi.portfolio;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;
import tech.radhi.portfolio.content.ContentTemplate;
import tech.radhi.portfolio.web.CloudflareService;
import tech.radhi.portfolio.web.WebScraper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final ContentService contentService;
    private final CloudflareService cloudflareService;
    private final WebScraper scraper;

    public MainService(ContentService service, CloudflareService cloudflareService, WebScraper scraper) {
        this.contentService = service;
        this.cloudflareService = cloudflareService;
        this.scraper = scraper;
    }

    public void getIndexContent(Model model) {
        var pics = contentService.getListOfContent("img");
        var map = pics.stream().collect(Collectors.toMap(
                ContentTemplate::id,
                ContentTemplate::contentBody,
                (_, newV) -> newV
        ));
        model.addAttribute("imgs", map);
    }

    public void handleHtmxRequests(Model model, String header) {
        if ("true".equals(header)) {
            model.addAttribute("headless", true);
        }
    }

    public void getAboutContent(Model model) {
        var skills = contentService.getContentById("skills").split("\\.");
        var questions = contentService.getListOfContent("q");
        var projects = contentService.getListOfProjects();

        model.addAttribute("questions", questions);
        model.addAttribute("projects", projects);

        if (skills.length <= 2) skills = new String[]{"sorry", "error", "happened"};
        model.addAttribute("frontend", List.of(skills[0].split(",")));
        model.addAttribute("backend", List.of(skills[1].split(",")));
        model.addAttribute("tools", List.of(skills[2].split(",")));
    }

    public void getInsightsContent(Model model) {

        int mockGitHubRepos = 30;
        int mockGitHubStars = 8;
        int mockCommitsLastMonth = 62;
        int skillsCount = 15;

        String beverage = "Tea & Coffee";
        String os = "Arch Linux";
        String ide = "IntelliJ IDEA / VS Code";
        String resumeSkill = "Java";

        var siteData = cloudflareService.getAnalytics()
                .get("viewer")
                .get("zones").get(0)
                .get("totals").get(0);
        String totalRequests = siteData.get("sum").get("requests").asText();
        String uniqueVisitors = siteData.get("uniq").get("uniques").asText();

        var uptimeData = scraper.fetchJsonNode(
                "https://raw.githubusercontent.com/bajahaw/web-monitor/master/history/summary.json"
        );
        String uptime = uptimeData.get(1).get("uptimeDay").asText();
        String availability = uptimeData.get(1).get("uptimeMonth").asText();


        InsightsData insightsData = new InsightsData(
                mockGitHubRepos,
                mockGitHubStars,
                mockCommitsLastMonth,
                skillsCount,
                beverage,
                os,
                ide,
                uptime,
                resumeSkill,
                availability,
                totalRequests,
                uniqueVisitors
        );
        model.addAttribute("insightsData", insightsData);
    }
}
