package tech.radhi.portfolio.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class PageRestController {

    private static final Logger log = LoggerFactory.getLogger(PageRestController.class);

    private final PageScraper scraper;

    public PageRestController(PageScraper scraper) {
        this.scraper = scraper;
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
}
