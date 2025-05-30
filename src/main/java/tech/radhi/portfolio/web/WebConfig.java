package tech.radhi.portfolio.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

@Configuration
public class WebConfig {
    @Bean
    public RestClient createRestClient(SimpleClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        return RestClient.create(restTemplate);
    }

    @Bean
    public HttpSyncGraphQlClient createGraphQlClient() {
        // A separate RestClient with base URL, without it, for some reason RestClient defaults to sending GET requests.
        RestClient newRestClient = RestClient.create("https://spring.io/graphql");
        return HttpSyncGraphQlClient.builder(newRestClient).build();
    }

    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        return new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(@NonNull HttpURLConnection connection, @NonNull String httpMethod) {
                connection.setInstanceFollowRedirects(true);
            }
        };
    }
}
