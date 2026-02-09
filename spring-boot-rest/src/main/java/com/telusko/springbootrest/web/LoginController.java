package com.telusko.springbootrest.web;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> loginPage(
            @RequestParam Optional<String> error,
            @RequestParam Optional<String> logout,
            CsrfToken csrfToken) {
        String message = "";
        if (error.isPresent()) {
            message = "<p style=\"color:#b00020;\">Invalid username or password.</p>";
        } else if (logout.isPresent()) {
            message = "<p style=\"color:#0f7b0f;\">You have been logged out.</p>";
        }

        String body = """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="UTF-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <title>Login</title>
                    <style>
                      body { font-family: Arial, sans-serif; background: #f6f8fb; }
                      .card { max-width: 420px; margin: 60px auto; padding: 24px; background: #fff; border-radius: 12px; box-shadow: 0 10px 30px rgba(0,0,0,0.08); }
                      h1 { margin: 0 0 16px; }
                      label { display: block; margin: 12px 0 6px; font-weight: 600; }
                      input { width: 100%; padding: 10px 12px; border: 1px solid #ccd3dd; border-radius: 8px; }
                      button { margin-top: 16px; width: 100%; padding: 12px; border: none; border-radius: 8px; background: #2f6fed; color: #fff; font-weight: 600; cursor: pointer; }
                      .hint { margin-top: 16px; font-size: 0.9rem; color: #5b6573; }
                    </style>
                  </head>
                  <body>
                    <div class="card">
                      <h1>Welcome back</h1>
                      %s
                      <form method="post" action="/login">
                        <input type="hidden" name="%s" value="%s" />
                        <label for="username">Username</label>
                        <input id="username" name="username" type="text" autocomplete="username" required />
                        <label for="password">Password</label>
                        <input id="password" name="password" type="password" autocomplete="current-password" required />
                        <button type="submit">Sign in</button>
                      </form>
                      <p class="hint">Demo credentials: <strong>user</strong> / <strong>password</strong></p>
                    </div>
                  </body>
                </html>
                """.formatted(message, csrfToken.getParameterName(), csrfToken.getToken());

        return ResponseEntity.ok(body);
    }
}
