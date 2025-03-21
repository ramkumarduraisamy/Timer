package com.surya.usetiming.controller;

import com.surya.usetiming.configuration.AppConfig;
import com.surya.usetiming.model.User;
import com.surya.usetiming.service.AzureGraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/graph")
public class AzureGraphController {

    private final AzureGraphService azureGraphService;
    private final String appRegistrationId;

    public AzureGraphController(AzureGraphService azureGraphService, AppConfig appConfig) {
        this.azureGraphService = azureGraphService;
        this.appRegistrationId = appConfig.getAppRegistrationId(); // Fetch value from AppConfig
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RegisteredOAuth2AuthorizedClient("azure-dev") OAuth2AuthorizedClient client) {
        try {
            var response = azureGraphService.getAllUsers(client);
            return response.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/users/import")
    public ResponseEntity<?> importAllUsers(
            @RegisteredOAuth2AuthorizedClient("azure-dev") OAuth2AuthorizedClient client)
    {
        try{
            List<User> users = azureGraphService.importAllUsers(client);

            return users == null ? ResponseEntity.internalServerError().build() : ResponseEntity.ok(users);
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}