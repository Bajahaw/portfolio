package tech.radhi.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.radhi.portfolio.dto.Page;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController()
@RequestMapping("/web")
public class WebRestController {

    private static final Logger log = LoggerFactory.getLogger(WebRestController.class);

    private final WebService scraper;
    private final CloudflareService cloudflareService;
    private final GithubService githubService;

    public WebRestController(WebService scraper, CloudflareService cloudflareService, GithubService githubService) {
        this.scraper = scraper;
        this.cloudflareService = cloudflareService;
        this.githubService = githubService;
    }

    @GetMapping("/scrape")
    public Page scrapePage(@RequestParam String url) {
        log.info("Starting to scrape page : {}", url);
        Page page = null;
        try {
            page = scraper.scrape(url);
            log.info("Page scraped in {} thread : {}", Thread.currentThread(), page.url());
        } catch (Exception e) {
            log.warn("Page {} could not scraped - {} ", url, e.getMessage());
        }
        return page;
    }

    @GetMapping("/scrapePagesIn")
    public String scrapePagesIn(@RequestParam String url) {
        Page scrapedPage = scrapePage(url);

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            scrapedPage
                    .links()
                    .forEach(link -> executor.execute(() -> scrapePage(link)));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "Discovered " + scrapedPage.links().size() + " links, inspect server logs to see the progress of the scraping.";
    }

    @GetMapping("/cloudflare")
    public JsonNode getAnalytics() {
        return cloudflareService.getAnalytics();
    }

    @GetMapping("/github")
    public JsonNode getGithubAnalytics() {
        return githubService.getAnalytics();
    }

    @ExceptionHandler(FieldAccessException.class)
    public ResponseEntity<Map<String, String>> handleFieldAccess(FieldAccessException ex) {
        return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", ex.getMessage()));
    }
}
