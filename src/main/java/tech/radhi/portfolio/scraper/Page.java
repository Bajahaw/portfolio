package tech.radhi.portfolio.scraper;

import java.net.URI;
import java.util.List;

public record Page(
        URI url,
        String title,
        List<String> keywords,
        List<String> links
) { }