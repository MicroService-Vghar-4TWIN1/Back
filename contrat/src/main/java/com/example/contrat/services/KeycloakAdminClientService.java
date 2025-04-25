package com.example.contrat.services;

import com.nimbusds.jwt.JWT;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class KeycloakAdminClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String keycloakBaseUrl = "http://localhost:8092";
    private final String realm = "projet"; // ton realm keycloak
    private final String clientId = "microservice"; // le client configuré

    public String getAccessToken() {
        String tokenUrl = "http://localhost:8092/realms/projet/protocol/openid-connect/token"; // Corrected URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", clientId); // Use the clientId variable
        map.add("client_secret", "fxlKyAdCIxk5uFgNF7wjlCFV7x6ocDvd");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    public String getUsernameFromToken(String token) {
        try {
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class)
                    .getToken();
            return accessToken.getPreferredUsername();
        } catch (VerificationException e) {
            throw new RuntimeException("Failed to decode token", e);
        }
    }



    public List<Map<String, Object>> getAllUsers() {
        try {
            String token = getAccessToken();
            String usersUrl = keycloakBaseUrl + "/admin/realms/" + realm + "/users";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.set("Accept", "application/json");

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<List> response = restTemplate.exchange(
                    usersUrl,
                    HttpMethod.GET,
                    request,
                    List.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch users from Keycloak", e);
        }
    }
}
