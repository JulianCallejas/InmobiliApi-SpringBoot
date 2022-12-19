package inmobiliapi.controller;

import inmobiliapi.security.AuthCredentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
@Tag(name = "auth", description = "API para el log-in")
public class LogInController {

    @Operation(summary = "Log-in de usuario", description = "")
    @PostMapping(value = "/auth/login")
    public void login(@RequestBody AuthCredentials credentials) {

    }

}
