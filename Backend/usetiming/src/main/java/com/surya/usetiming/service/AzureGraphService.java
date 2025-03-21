package com.surya.usetiming.service;

import com.surya.usetiming.configuration.AppConfig;
import com.surya.usetiming.model.Role;
import com.surya.usetiming.model.User;
import com.surya.usetiming.repository.UserRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AzureGraphService {

    // Constants
    private static final String QUERY_FIELDS = "/users?$select=displayName,mail";
    private static final String NEXT_PAGE_KEY = "@odata.nextLink";

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final UserRepository userRepository;
    private Role role;

    public AzureGraphService(AppConfig appConfig, RestTemplate restTemplate, HttpHeaders headers, UserRepository userRepository) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.userRepository = userRepository;
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

    public List<User> importAllUsers(OAuth2AuthorizedClient client) {
        List<Map<String,Object>> allUsers = this.getAllUsers(client);

//        List<User> users =  allUsers.stream()
//                .filter(user -> user.get("mail") != null && !user.get("mail").toString().isEmpty())
//                .map(user ->{
//                            User newUser = new User(user.get("displayName").toString(),
//                            user.get("mail").toString());
//
//                            newUser.setRole();
//
//
//                        }
//                )
//                .toList();

        return null;
    }
}
