package tech.radhi.portfolio.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tech.radhi.portfolio.dto.Page;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

@Component
public class WebService {

    private final RestClient restClient;
    private final ObjectMapper mapper;

    public WebService(RestClient restClient, ObjectMapper mapper) {
        this.restClient = restClient;
        this.mapper = mapper;
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

    public <T> T scrape(String url, Function<Document,T> mapper) {
        Document doc = parse(downloadPage(url));
        return mapper.apply(doc);
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

    public String fetchJson(String url) {
        return restClient.get()
                .uri(url).
                retrieve()
                .body(String.class);
    }

    public JsonNode fetchJsonNode(String url) {
        try {
            return mapper.readTree(fetchJson(url));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }


    private static String getHref(String url, String href) {
        if (!href.startsWith("http")) {
            URI uri = URI.create(url);
            return uri.getScheme() + "://" + uri.getHost() + (!href.startsWith("/") ? "/" : "") + href;
        }
        return href;
    }
}


