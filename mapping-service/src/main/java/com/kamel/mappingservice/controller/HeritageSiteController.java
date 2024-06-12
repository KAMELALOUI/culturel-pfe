package com.kamel.mappingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamel.mappingservice.dto.AuthCheckResponseObject;
import com.kamel.mappingservice.dto.JsonResponse;
import com.kamel.mappingservice.model.HeritageSite;
import com.kamel.mappingservice.repository.HeritageSiteRepository;
import com.kamel.mappingservice.service.CheckAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/heritage")
public class HeritageSiteController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/heritage/";

    @Autowired
    public CheckAuth checkAuth;

    @Autowired
    private HeritageSiteRepository heritageSiteRepository;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllSites() {
        return ResponseEntity.ok(heritageSiteRepository.findAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createSite(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("description") String description
    ) throws IOException, InterruptedException, URISyntaxException {
        System.out.println(token);
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<AuthCheckResponseObject> futureResponse = client.sendAsync(checkAuth.checkAuth(token), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        AuthCheckResponseObject responseObject = objectMapper.readValue(responseBody, AuthCheckResponseObject.class);
                        System.out.println("Parsed response (Jackson): " + responseObject);
                        return responseObject;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new AuthCheckResponseObject(false);
                    }
                });

        AuthCheckResponseObject result = futureResponse.join();

        if (result.isSuccess()) {
            HeritageSite heritageSite = new HeritageSite();
            heritageSite.setName(name);
            heritageSite.setLocation(location);
            heritageSite.setDescription(description);

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(false, "bad image !!"));
            } else {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

                    // Ensure the directory exists
                    if (!Files.exists(path.getParent())) {
                        Files.createDirectories(path.getParent());
                    }

                    Files.write(path, bytes);

                    heritageSite.setImageURL("http://localhost:8087/uploads/heritage/" + file.getOriginalFilename());
                    heritageSiteRepository.save(heritageSite);

                    return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(true, "Heritage site published successfully."));
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Failed to upload the file"));
                }
            }
        } else {
            return ResponseEntity.status(401).body(new JsonResponse(false, "Session expired"));
        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> searchSites(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String keyword) {
        List<HeritageSite> results;
        if (name != null && !name.isEmpty()) {
            results = heritageSiteRepository.findByNameContainingIgnoreCase(name);
        } else if (location != null && !location.isEmpty()) {
            results = heritageSiteRepository.findByLocationContainingIgnoreCase(location);
        } else if (keyword != null && !keyword.isEmpty()) {
            results = heritageSiteRepository.searchByDescription(keyword);
        } else {
            results = heritageSiteRepository.findAll();
        }
        return ResponseEntity.ok(results);
    }
}
