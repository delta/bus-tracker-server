package edu.nitt.delta.bustracker.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUtil {

    @Value("${dAuth.host}")
    private String dAuthHost;

    public WebClient dAuthWebClient() {
        return WebClient.builder()
                .baseUrl(dAuthHost)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }
}
