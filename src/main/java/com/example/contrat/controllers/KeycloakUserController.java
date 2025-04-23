/*package com.example.contrat.controllers;

import com.example.contrat.services.KeycloakAdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

@RequestMapping("/admin")
public class KeycloakUserController {

    @Autowired
    private KeycloakAdminClientService keycloakService;

    @GetMapping("/keycloak-users")
    @PreAuthorize("hasRole('admin')") // très important
    public ResponseEntity<List<Map<String, Object>>> getKeycloakUsers() {
        return ResponseEntity.ok(keycloakService.getAllUsers());
    }
}
*/