package tech.radhi.portfolio;

public record InsightsData(
        int gitHubRepositories,
        int gitHubStars,
        int commitsLastMonth,
        int identifiedSkillsCount,
        String preferredBeverage,
        String operatingSystem,
        String idePref,
        String uptimeStatus,
        String topResumeSkill,
        long totalWebsiteVisits,
        long uniqueVisitors,
        double availabilityLast30Days
) {}