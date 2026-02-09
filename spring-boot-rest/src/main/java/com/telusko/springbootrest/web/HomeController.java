package com.telusko.springbootrest.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> home() {
        String body = """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="UTF-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <title>Home</title>
                  </head>
                  <body>
                    <h1>Login successful</h1>
                    <p>You are signed in.</p>
                    <p><a href="/logout">Sign out</a></p>
                  </body>
                </html>
                """;
        return ResponseEntity.ok(body);
    }
}
