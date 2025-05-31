package tech.radhi.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tech.radhi.portfolio.content.ContentService;
import tech.radhi.portfolio.dto.CloudflareStats;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.dto.GithubStats;
import tech.radhi.portfolio.dto.UptimeStats;
import tech.radhi.portfolio.web.CloudflareService;
import tech.radhi.portfolio.web.GithubService;
import tech.radhi.portfolio.web.WebService;

import java.util.List;import java.util.stream.Collectors;

@Service
public class MainService {
    private static final Logger log = LoggerFactory.getLogger(MainService.class);
    private final ContentService contentService;
    private final CloudflareService cloudflareService;
    private final GithubService githubService;
    private final WebService webService;

    public MainService(
            WebService webService,
            ContentService service,
            GithubService githubService,
            CloudflareService cloudflareService
    ) {
        this.contentService = service;
        this.cloudflareService = cloudflareService;
        this.githubService = githubService;
        this.webService = webService;
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

    public void getFAQ(Model model) {
        var questions = contentService.getListOfContent("q");
        model.addAttribute("questions", questions);
    }

    public void getSkills(Model model) {
        var skills = contentService.getContentById("skills").split("\\.");
        if (skills.length <= 2) skills = new String[]{"sorry", "error", "happened"};
        model.addAttribute("frontend", List.of(skills[0].split(",")));
        model.addAttribute("backend", List.of(skills[1].split(",")));
        model.addAttribute("tools", List.of(skills[2].split(",")));
    }

    public void getProjects(Model model) {
        var projects = contentService.getListOfProjects();
        model.addAttribute("projects", projects);
    }

    public UptimeStats getUptimeStats() {
        try {
            var uptimeData = webService.fetchJsonNode(
                    "https://raw.githubusercontent.com/bajahaw/web-monitor/master/history/summary.json"
            );
            String uptimeDay = uptimeData.get(1).get("uptimeDay").asText();
            String uptimeMonth = uptimeData.get(1).get("uptimeMonth").asText();
            return new UptimeStats(uptimeDay, uptimeMonth);
        }
        catch (Exception e) {
            log.error("Error, unable to get Upptime stats: {}", e.getMessage());
            return new UptimeStats("99%?", "97%?");
        }
    }

    public CloudflareStats getCloudflareStats () {
        try{
            var siteData = cloudflareService.getAnalytics()
                    .path("viewer")
                    .path("zones").get(0)
                    .path("totals").get(0);
            String totalRequests = siteData.path("sum").path("requests").asText();
            String uniqueVisitors = siteData.path("uniq").path("uniques").asText();

            return new CloudflareStats(totalRequests, uniqueVisitors);
        }
        catch (Exception e) {
            log.error("Error, unable to get Cloudflare Stats: {}", e.getMessage());
            return new CloudflareStats("1k?", "100?");
        }
    }

    public GithubStats getGithubStats() {
        try {
            var githubData = githubService.getAnalytics().get("user");
            int publicRepos = githubData
                    .path("publicRepos") // Safe null check via path()
                    .path("totalCount")
                    .asInt(0);

            int stars = githubData
                    .findValues("stargazers")
                    .stream()
                    .map(node -> node.path("totalCount").asInt())
                    .reduce(Integer::sum)
                    .orElse(0);

            int recentCommits = githubData
                    .path("contributionsCollection")
                    .path("totalCommitContributions")
                    .asInt(0);

            return new GithubStats(publicRepos, stars, recentCommits);

        } catch (Exception e) {
            log.error("Error, unable to get github stats: {}", e.getMessage());
            return new GithubStats(0, 0, 0);
        }
    }
}

