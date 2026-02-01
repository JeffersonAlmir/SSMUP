package com.br.ssmup.controller;

import com.br.ssmup.dto.AuthResponse;
import com.br.ssmup.dto.GoogleLoginRequest;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.repository.UsuarioRepository;
import com.br.ssmup.service.AuthService;
import com.br.ssmup.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthService authService, TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> loginGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        String jwt = authService.loginGoogle(request.token());
        return ResponseEntity.ok(new AuthResponse(jwt, "Bearer", tokenService.getExpiration()));
    }

    @PostMapping("/teste")
    public ResponseEntity<AuthResponse> loginTeste(@Valid @RequestBody GoogleLoginRequest request) {
        String email = request.token();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado no banco"));

        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new AuthResponse(token, "Bearer", tokenService.getExpiration()));
    }
}

