package com.br.ssmup.service;

import com.br.ssmup.config.google.GoogleTokenVerifier;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.exceptions.AuthenticationException;
import com.br.ssmup.exceptions.UnauthorizedException;
import com.br.ssmup.repository.UsuarioRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final GoogleTokenVerifier googleTokenVerifier;

    public AuthService(UsuarioRepository usuarioRepository, TokenService tokenService, GoogleTokenVerifier googleTokenVerifier) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.googleTokenVerifier = googleTokenVerifier;
    }

    public String loginGoogle(String googleToken) {

        var payload = googleTokenVerifier.verify(googleToken);

        String email = payload.getEmail();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("Usuário não autorizado"));

        return tokenService.gerarToken(usuario);


    }
}
