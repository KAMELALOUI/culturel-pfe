package com.kamel.sitepatrimonial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamel.sitepatrimonial.dto.AuthCheckResponseObject;
import com.kamel.sitepatrimonial.dto.JsonResponse;
import com.kamel.sitepatrimonial.models.patrimonial;
import com.kamel.sitepatrimonial.repository.PatrimonialRepository;
import com.kamel.sitepatrimonial.services.CheckAuth;
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
@RequestMapping("/api/sites")
public class PatrimonialController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/patrimonial/";

    @Autowired
    public CheckAuth checkAuth;

    @Autowired
    private PatrimonialRepository patrimonialRepository;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createSite(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam("file") MultipartFile file,
            @RequestParam("nom") String nom,
            @RequestParam("localisation") String localisation,
            @RequestParam("descriptionHistorique") String descriptionHistorique
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
            patrimonial Patrimonial = new patrimonial();
            Patrimonial.setNom(nom);
            Patrimonial.setLocalisation(localisation);
            Patrimonial.setDescriptionHistorique(descriptionHistorique);

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(false, "Bad image !!"));
            } else {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

                    // Ensure the directory exists
                    if (!Files.exists(path.getParent())) {
                        Files.createDirectories(path.getParent());
                    }

                    Files.write(path, bytes);

                    Patrimonial.setImageURL("http://localhost:8085/uploads/patrimonial/" + file.getOriginalFilename());
                    patrimonialRepository.save(Patrimonial);

                    return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(true, "Patrimonial site published successfully.", Patrimonial));
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Failed to upload the file"));
                }
            }
        } else {
            return ResponseEntity.status(401).body(new JsonResponse(false, "Session expired"));
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllSites() {
        List<patrimonial> sites = patrimonialRepository.findAll();
        if (sites.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponse(false, "No sites found"));
        } else {
            return ResponseEntity.ok(sites);
        }
    }
}
