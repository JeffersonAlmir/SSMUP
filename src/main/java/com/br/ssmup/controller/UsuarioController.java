package com.br.ssmup.controller;

import com.br.ssmup.entities.Usuario;
import com.br.ssmup.service.EmpresaService;
import com.br.ssmup.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(){
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }
}
