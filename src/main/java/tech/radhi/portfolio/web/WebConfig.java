package tech.radhi.portfolio.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.HttpURLConnection;

@Configuration
public class WebConfig {
    @Bean
    public RestClient createRestClient(SimpleClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        return RestClient.create(restTemplate);
    }

    @Bean
    public WebClient createWebClient() {
        return WebClient.create();
    }

    @Bean
    public HttpGraphQlClient createGraphQlClient(WebClient webClient) {
        return HttpGraphQlClient.builder(webClient).build();
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
