package tech.radhi.portfolio.content;

import java.util.List;

public record ProjectTemplate(
        int year,
        String title,
        String description,
        List<String> technologies,
        String imageUrl
) { }
