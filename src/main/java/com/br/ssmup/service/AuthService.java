package com.br.ssmup.service;

import com.br.ssmup.components.GoogleTokenVerifier;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.exceptions.UnauthorizedException;
import com.br.ssmup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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

        Usuario usuario = usuarioRepository.findByEmailAndAtivo(email, true).orElseThrow(() -> new UnauthorizedException("Usuário não autorizado"));

        return tokenService.gerarToken(usuario);


    }
}
