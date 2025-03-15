package com.surya.usetiming.controller;

import com.surya.usetiming.service.AzureGraphService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/graph/")
public class AzureGraphController {
    private final AzureGraphService azureGraphService;

    public AzureGraphController(AzureGraphService azureGraphService) {
        this.azureGraphService = azureGraphService;
    }

    @GetMapping("getAllUsers")
    public ResponseEntity getAllUsers(@RegisteredOAuth2AuthorizedClient("azure-dev") OAuth2AuthorizedClient client) {
        try
        {
            var response = this.azureGraphService.getAllUsers(client);
            if (response.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
