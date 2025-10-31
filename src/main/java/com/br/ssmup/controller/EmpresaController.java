package com.br.ssmup.controller;

import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll(){
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok().body(empresas);
    }

    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody Empresa empresa){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(empresa));
    }

    @PostMapping("{id}/licensasSanitarias")
    public ResponseEntity<LicensaSanitaria> saveLincensaSanitaria(@PathVariable Long id, @RequestBody LicensaSanitaria licensa){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveLicensaSanitaria(id,licensa));
    }
}
