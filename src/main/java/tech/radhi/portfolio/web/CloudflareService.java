package tech.radhi.portfolio.web;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class CloudflareService {

    private final String ZONE;
    private final HttpGraphQlClient graphQlClient;

    public CloudflareService(
            @Value("${cloudflare.token}") String token,
            @Value("${cloudflare.zone}") String zone,
            HttpGraphQlClient graphQlClient
    ) {
        this.ZONE = zone;
        this.graphQlClient = graphQlClient
                .mutate()
                .url("https://api.cloudflare.com/client/v4/graphql")
                .header("Authorization", "Bearer " + token)
                .build();
    }

    public JsonNode getAnalytics() {
        //language=graphql
        String doc = """ 
                query GetZoneAnalytics( $zoneTag: String, $since: String, $until: String ) {
                  viewer {
                    zones(filter: { zoneTag: $zoneTag }) {
                      totals: httpRequests1dGroups(
                        limit: 10000
                        filter: { date_geq: $since, date_lt: $until }
                      ) {
                        sum   { requests }
                        uniq  { uniques }
                      }
                    }
                  }
                }
                """;

        Map<String, Object> vars = Map.of(
                "zoneTag", ZONE,
                "since", LocalDate.now().minusDays(7).toString(),
                "until", LocalDate.now().toString()
        );

        return graphQlClient
                .document(doc)
                .variables(vars)
                .executeSync()
                .toEntity(JsonNode.class);
    }
}
