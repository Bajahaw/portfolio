package tech.radhi.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.radhi.portfolio.dto.Page;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class WebRestController {

    private static final Logger log = LoggerFactory.getLogger(WebRestController.class);

    private final WebScraper scraper;
    private final CloudflareService cloudflareService;

    public WebRestController(WebScraper scraper, CloudflareService service, CloudflareService cloudflareService) {
        this.scraper = scraper;
        this.cloudflareService = cloudflareService;
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

    @ExceptionHandler(FieldAccessException.class)
    public ResponseEntity<Map<String, String>> handleFieldAccess(FieldAccessException ex) {
        return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", ex.getMessage()));
    }
}
