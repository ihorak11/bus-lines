package com.example.buslines.api.client;

import com.example.buslines.config.ApplicationProperties;
import com.example.buslines.model.JourneyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class SLApiClient extends ApiClient {

    public static final String QUERY_PARAM_NAME_MODEL = "model";
    public static final String RESPONSE_MODEL_JOURNEY = "jour"; //this would usually be a Enum but not necessary now
    public static final String DEFAULT_TRANSPORT_MODE_CODE_BUS = "BUS"; //this would usually be a Enum but not necessary now
    public static final String QUERY_PARAM_NAME_DTMC = "DefaultTransportModeCode";
    public static final String QUERY_PARAM_NAME_KEY = "key";

    public SLApiClient(WebClient.Builder clientBuilder, ApplicationProperties appProps) { //This constructor is implicitly Autowired
        super(clientBuilder.baseUrl(appProps.getApiSlUrl()).build(), appProps);
    }

    public JourneyResponse getSlBusLines() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(QUERY_PARAM_NAME_MODEL, RESPONSE_MODEL_JOURNEY);
        queryParams.add(QUERY_PARAM_NAME_DTMC, DEFAULT_TRANSPORT_MODE_CODE_BUS);
        queryParams.add(QUERY_PARAM_NAME_KEY, getAppProps().getSlApiKey());

        return apiRequest(HttpMethod.GET, getAppProps().getSlApiPath(), JourneyResponse.class, queryParams);
    }
}
