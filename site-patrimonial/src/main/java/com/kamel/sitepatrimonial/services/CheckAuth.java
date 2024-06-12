package com.kamel.sitepatrimonial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class CheckAuth {





    public HttpRequest checkAuth(String token) throws URISyntaxException, InterruptedException, IOException {

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .uri(new URI("http://localhost:8088/api/auth/verify")).build();

        return request;
    }
}
