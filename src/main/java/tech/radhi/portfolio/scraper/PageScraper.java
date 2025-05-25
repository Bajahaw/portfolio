package tech.radhi.portfolio.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Optional;

@Component
public class PageScraper {

    private final RestClient restClient;

    public PageScraper(RestClient restClient) {
        this.restClient = restClient;
    }

    private String downloadPage(String url) {
        return restClient
                .get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }


    private Document parse(String htmlContent) {
        return Jsoup.parse(Optional.ofNullable(htmlContent).orElse("<body></body>"));
    }

    public Page scrape(String url) {
        Document document = parse(downloadPage(url));
        return new Page(
                URI.create(url),
                document.title(),
                document.select("meta[name=keywords]").eachAttr("content"),
                document.select("a[href]")
                        .stream()
                        .map(element -> getHref(url, element.attr("href")))
                        .filter(href -> !href.isBlank())
                        .toList()
        );
    }

    private static String getHref(String url, String href) {
        if (!href.startsWith("http")) {
            URI uri = URI.create(url);
            return uri.getScheme() + "://" + uri.getHost() + (!href.startsWith("/") ? "/" : "") + href;
        }
        return href;
    }
}


