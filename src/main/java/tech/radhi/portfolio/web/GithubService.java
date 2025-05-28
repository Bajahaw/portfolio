package tech.radhi.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class GithubService {

    private final HttpGraphQlClient graphQlClient;

    public GithubService(HttpGraphQlClient graphQlClient, @Value("${github.token}") String token) {
        this.graphQlClient = graphQlClient
                .mutate()
                .url("https://api.github.com/graphql")
                .header("Authorization", "Bearer " + token)
                .build();
    }

    public JsonNode getAnalytics() {
        //language=graphql
        String doc = """
                query($login: String!, $from: DateTime!, $to: DateTime!) {
                  user(login: $login) {
                    login
                    publicRepos: repositories(privacy: PUBLIC) {
                      totalCount
                    }
                    repositories(first: 100, privacy: PUBLIC, ownerAffiliations: OWNER) {
                      nodes {
                        stargazers {
                          totalCount
                        }
                      }
                    }
                    contributionsCollection(from: $from, to: $to) {
                      totalCommitContributions
                      totalIssueContributions
                      totalPullRequestContributions
                      totalPullRequestReviewContributions
                      contributionCalendar {
                        totalContributions
                      }
                    }
                  }
                }
                """;

        Map<String, Object> vars = Map.of(
                "from", LocalDateTime.now().minusDays(30).toString(),
                "to", LocalDateTime.now().toString(),
                "login", "Bajahaw"
        );

        return graphQlClient
                .document(doc)
                .variables(vars)
                .executeSync().
                toEntity(JsonNode.class);
    }
}
