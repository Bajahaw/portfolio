package tech.radhi.portfolio.web;

import java.net.URI;
import java.util.List;

public record Page(
        URI url,
        String title,
        List<String> keywords,
        List<String> links
) { }