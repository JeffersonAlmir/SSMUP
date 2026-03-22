package com.br.ssmup.controller;

import com.br.ssmup.dto.AuthResponse;
import com.br.ssmup.dto.GoogleLoginRequest;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.repository.UsuarioRepository;
import com.br.ssmup.service.AuthService;
import com.br.ssmup.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Autenticação", description = "Gerenciamento de Login e Tokens (OAuth2/JWT)")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/google")
    @Operation(summary = "Login com Google", description = "Autentica o usuário validando o token do Google e retorna um JWT da aplicação.")
    public ResponseEntity<AuthResponse> loginGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        String jwt = authService.loginGoogle(request.token());
        return ResponseEntity.ok(new AuthResponse(jwt, "Bearer", tokenService.getExpiration()));
    }

    @GetMapping("/ativar-conta")
    @Operation(summary = "Ativar conta", description = "Ativa a conta do usuario usando o token enviado por email.")
    public ResponseEntity<Map<String, String>> ativarConta(@RequestParam String token) {
        authService.ativarConta(token);
        return ResponseEntity.ok(Map.of("message", "Conta ativado com sucesso"));
    }
}

