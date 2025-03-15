package com.surya.usetiming.service;

import com.azure.core.http.HttpHeader;
import com.surya.usetiming.configuration.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AzureGraphService {

    //URLs
    private final String baseUrl;
    private static final String QUERY_FIELDS = "/users?$select=givenName,surname,displayName,mail";
    private static final String NEXT_PAGE_KEY = "@odata.nextLink";

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public AzureGraphService(RestTemplate restTemplate, HttpHeaders headers, String baseUrl) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.baseUrl = baseUrl;
    }

    public List<Map<String, Object>> getAllUsers(OAuth2AuthorizedClient client) {
        String accessToken = client.getAccessToken().getTokenValue();

        String url = this.baseUrl + QUERY_FIELDS;
        this.headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<Map<String, Object>> allUsers = new ArrayList<>();

        while (url != null)
        {
            ResponseEntity<Map> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                List<Map<String, Object>> users = (List<Map<String, Object>>) response.getBody().get("value");
                allUsers.addAll(users);

                try
                {
                    url = URLDecoder.decode((String) response.getBody().get(NEXT_PAGE_KEY), StandardCharsets.UTF_8);
                }
                catch (NullPointerException e) {
                    //No next URL(s) found.
                    break;
                }
            }
        }

        return allUsers;
    }

    
}
