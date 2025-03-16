package com.surya.usetiming.service;

import com.surya.usetiming.configuration.AppConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AzureGraphService {

    // Constants
    private static final String QUERY_FIELDS = "/users?$select=givenName,surname,displayName,mail";
    private static final String NEXT_PAGE_KEY = "@odata.nextLink";

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public AzureGraphService(AppConfig appConfig, RestTemplate restTemplate, HttpHeaders headers) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
        this.headers = headers;
    }

    /**
     * Get all users from azure ad.
     * @param client
     * @return List of users
     */
    public List<Map<String, Object>> getAllUsers(OAuth2AuthorizedClient client) {
        String accessToken = client.getAccessToken().getTokenValue();
        headers.setBearerAuth(accessToken);

        String url = appConfig.getGraphAPIUrl() + QUERY_FIELDS;
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<Map<String, Object>> allUsers = new ArrayList<>();

        while (url != null) {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Map<String, Object>> users = (List<Map<String, Object>>) response.getBody().get("value");
                allUsers.addAll(users);

                url = response.getBody().containsKey(NEXT_PAGE_KEY)
                        ? URLDecoder.decode((String) response.getBody().get(NEXT_PAGE_KEY), StandardCharsets.UTF_8)
                        : null;
            }
        }

        return allUsers;
    }
}
