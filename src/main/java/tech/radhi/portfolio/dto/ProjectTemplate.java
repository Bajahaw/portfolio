package tech.radhi.portfolio.dto;

import java.util.List;

public record ProjectTemplate(
        int year,
        String title,
        String description,
        List<String> technologies,
        String imageUrl,
        String repoLink,
        String liveLink
) { }
