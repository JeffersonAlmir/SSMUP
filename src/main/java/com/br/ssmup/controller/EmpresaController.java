package com.br.ssmup.controller;

import com.br.ssmup.dto.EmpresaCadastroDto;
import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.dto.LicensaSanitariaCadastroDto;
import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.service.EmpresaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<EmpresaResponseDto>> getAllEmpresas() {
        return ResponseEntity.ok().body(empresaService.listarEmpresas());
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDto> postEmpresas(@RequestBody @Valid EmpresaCadastroDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(payload));
    }

    @GetMapping("{id}/licensasSanitarias")
    public ResponseEntity<List<LicensaSanitariaResponseDto>> getAllLicensasSanitarias(@PathVariable Long id){
        return ResponseEntity.ok().body(empresaService.listarLicensasSanitarias(id));
    }

    @PostMapping("{id}/licensasSanitarias")
    public ResponseEntity<LicensaSanitariaResponseDto> saveLincensaSanitaria(@PathVariable Long id, @RequestBody LicensaSanitariaCadastroDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveLicensaSanitaria(id,payload));
    }
}
