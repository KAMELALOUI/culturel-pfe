package com.ttc.spring.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttc.spring.dto.AuthCheckResponseObject;
import com.ttc.spring.dto.JsonResponse;
import com.ttc.spring.entities.Article;
import com.ttc.spring.repositories.ArticleRepository;
import com.ttc.spring.services.CheckAuth;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";



    @Autowired
    public CheckAuth checkAuth;


    @Autowired
    public ArticleRepository repo;



    @GetMapping("/list")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

    public ResponseEntity<?> createArticle( ){
        return ResponseEntity.ok(this.repo.findAll());
    }





    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

    public ResponseEntity<?> createArticle(
            @RequestHeader( name="Authorization" ) String token, @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("descreption") String descreption  )throws IOException, InterruptedException, URISyntaxException{
        System.out.println(token);
        HttpClient client = HttpClient.newHttpClient();


        CompletableFuture<AuthCheckResponseObject> futureResponse = client.sendAsync(this.checkAuth.checkAuth(token), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> {
                    try {
                        // Parse JSON response using Jackson
                        ObjectMapper objectMapper = new ObjectMapper();
                        AuthCheckResponseObject responseObject = objectMapper.readValue(responseBody, AuthCheckResponseObject.class);
                        System.out.println("Parsed response (Jackson): " + responseObject);


                        return responseObject;
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle exception and return a default/fallback value
                        return new AuthCheckResponseObject(false);
                    }
                });

        AuthCheckResponseObject result = futureResponse.join();


        if (result.isSuccess())  {

            Article article = new Article();

            article.setTitle(title);
            article.setContent(descreption);


            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new JsonResponse(false, "bad image !!")  );
            }else {
                try {
                    // Save the file locally
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                    Files.write(path, bytes);


                    article.setImageURL( "http://localhost:8084/uploads/"+ file.getOriginalFilename()  );

                    this.repo.save(article);


                    return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(true,"Article published successfully."));

                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(true,"Failed to upload the file"));
                }
            }





        }else {
            return ResponseEntity.status(401).body( new JsonResponse(false, "Session expired"));

        }








    }

}