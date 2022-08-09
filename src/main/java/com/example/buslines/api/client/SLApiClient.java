package com.example.buslines.api.client;

import com.example.buslines.config.ApplicationProperties;
import com.example.buslines.api.response.JourneyPatternStopResponse;
import com.example.buslines.api.response.StopPointResponse;
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
    public static final String RESPONSE_MODEL_STOP = "stop"; //this would usually be a Enum but not necessary now
    public static final String DEFAULT_TRANSPORT_MODE_CODE_BUS = "BUS"; //this would usually be a Enum but not necessary now
    public static final String QUERY_PARAM_NAME_DTMC = "DefaultTransportModeCode";
    public static final String QUERY_PARAM_NAME_KEY = "key";

    public SLApiClient(WebClient.Builder clientBuilder, ApplicationProperties appProps) { //This constructor is implicitly Autowired
        super( appProps, clientBuilder.baseUrl(appProps.getApiSlUrl()).build());
    }

    public JourneyPatternStopResponse getSlBusLines() {
        log.info("Calling API for GET bus journey details");

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(QUERY_PARAM_NAME_MODEL, RESPONSE_MODEL_JOURNEY);
        queryParams.add(QUERY_PARAM_NAME_DTMC, DEFAULT_TRANSPORT_MODE_CODE_BUS);
        queryParams.add(QUERY_PARAM_NAME_KEY, getAppProps().getSlApiKey());

        return callSLStopsAndLinesApi(getAppProps().getSlApiPath(), JourneyPatternStopResponse.class, queryParams);
    }

    public StopPointResponse getStopPointDetails() {
        log.info("Calling API for GET stop point details");

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(QUERY_PARAM_NAME_MODEL, RESPONSE_MODEL_STOP);
        queryParams.add(QUERY_PARAM_NAME_DTMC, DEFAULT_TRANSPORT_MODE_CODE_BUS);
        queryParams.add(QUERY_PARAM_NAME_KEY, getAppProps().getSlApiKey());

        return callSLStopsAndLinesApi(getAppProps().getSlApiPath(), StopPointResponse.class, queryParams);
    }
        private <K> K callSLStopsAndLinesApi(String path, Class<K> responseClass, MultiValueMap<String, String> queryParams) {
            return apiRequest(HttpMethod.GET, path, responseClass, queryParams);
        }
    }


