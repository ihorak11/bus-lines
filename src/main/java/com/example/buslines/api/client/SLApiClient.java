package com.example.buslines.api.client;

import com.example.buslines.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class SLApiClient extends ApiClient {

    public SLApiClient(WebClient.Builder clientBuilder, ApplicationProperties appProps) { //This constructor is implicitly Autowired
        super(clientBuilder.baseUrl(appProps.getApiSlUrl()).build(), appProps);
    }

    //TODO add API call method here
}
