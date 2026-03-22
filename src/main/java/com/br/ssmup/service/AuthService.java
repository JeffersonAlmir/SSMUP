package com.br.ssmup.service;

import com.br.ssmup.components.GoogleTokenVerifier;
import com.br.ssmup.entities.Usuario;
import com.br.ssmup.exceptions.BusinessRuleException;
import com.br.ssmup.exceptions.UnauthorizedException;
import com.br.ssmup.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
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
        log.info("Iniciando login com google");
        var payload = googleTokenVerifier.verify(googleToken);

        String email = payload.getEmail();

        Usuario usuario = usuarioRepository.findByEmailAndAtivo(email, true)
                .orElseThrow( () -> {
                    log.error("Falha ao validar usuario com email {}", email);
                    return new UnauthorizedException("Usuário não autorizado");
                });

        log.info("Login sucesso com email: {}, gerando token jwt!", email);
        return tokenService.gerarToken(usuario);
    }

    @Transactional
    public void ativarConta(String token) {
        log.info("Tentado ativar conta com token: {}", token);

        Usuario usuario = usuarioRepository.findByTokenAtivacao(token).orElseThrow(() -> {
            log.error("token de ativação invalido: {}", token);
            return new BusinessRuleException("Token de ativação invalido");
        });

        if(usuario.getDataExpiracaoToken().isBefore(LocalDateTime.now())){
            log.error("Token de ativação expirado para o usuario: {}", usuario.getEmail());
            throw new BusinessRuleException("Token expirado para o usuario. Solicite um novo ao seu coodernador");
        }

        usuario.setAtivo(true);
        usuario.setTokenAtivacao(null);
        usuario.setDataExpiracaoToken(null);
        usuarioRepository.save(usuario);

        log.info("Conta ativada com sucesso: {}", usuario.getEmail());
    }
}
