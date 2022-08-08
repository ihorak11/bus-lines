package com.example.buslines.api.client;

import com.example.buslines.config.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
public class ApiClient {

    private ApplicationProperties appProps;
    private WebClient webClient;


    public <K> K apiRequest(HttpMethod method, String path, Class<K> responseClass, MultiValueMap<String, String> queryParams, Object... pathParams) {
        return webClient.method(method) //TODO add headers as per Trafiklab.se
                .uri(uriBuilder ->
                        uriBuilder
                                .path(path)
                                .queryParams(queryParams)
                                .build(pathParams))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> {
                    String errorMsg = "Response returned with code: " + clientResponse.statusCode();
                    return Mono.error(new RuntimeException(errorMsg)); //TODO add exception handler
                })
                .bodyToMono(responseClass)
                .block();
    }
}
