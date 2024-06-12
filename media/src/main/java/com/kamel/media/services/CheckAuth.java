package com.kamel.media.services;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

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