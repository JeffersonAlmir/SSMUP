package com.br.ssmup.controller;

import com.br.ssmup.dto.*;
import com.br.ssmup.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    //Listar todas as empresas
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDto>> getAllEmpresas() {
        return ResponseEntity.ok().body(empresaService.listarEmpresas());
    }

    //Listar todas as empresas ativas
    @GetMapping("ativas")
    public ResponseEntity<List<EmpresaResponseDto>> getAllEmpresasAtivas() {
        return ResponseEntity.ok().body(empresaService.listarEmpresasAtivas());
    }

    //Listar todas as empresas inativas
    @GetMapping("inativas")
    public ResponseEntity<List<EmpresaResponseDto>> getAllEmpresasInativas() {
        return ResponseEntity.ok().body(empresaService.listarEmpresasInativas());
    }

    //Buscar empresa por ID
    @GetMapping({"{id}"})
    public ResponseEntity<EmpresaResponseDto> getEmpresas(@PathVariable Long id) {
        return ResponseEntity.ok().body(empresaService.getEmpresaById(id));
    }

    //Criar Empresa
    @PostMapping
    public ResponseEntity<EmpresaResponseDto> postEmpresas(@RequestBody @Valid EmpresaCadastroDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(payload));
    }

    //Atualizar Empresa por ID
    @PutMapping("{id}")
    public ResponseEntity<EmpresaAtualizarDto> updateEmpresas(@PathVariable Long id, @RequestBody @Valid EmpresaAtualizarDto payload){
        return ResponseEntity.ok(empresaService.atualizarEmpresa(id, payload));
    }

    //Atulazar o Endereco de uma empresa pelo Id
    @PutMapping("{id}/enderecos")
    public ResponseEntity<EnderecoResponseDto> updateEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoAtualizarDto payload){
        return ResponseEntity.ok(empresaService.atualizarEndereco(id, payload));
    }

    @PutMapping("{id}/responsaveis")
    public ResponseEntity<ResponsavelResponseDto> updateResponsavel(@PathVariable Long id, @RequestBody @Valid ResponsavelAtualizarDto payload){
        return ResponseEntity.ok(empresaService.atualizarResponsavel(id, payload));
    }

    //Deletar empresa por id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id){
        empresaService.deleteByIdEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    //Inativar empresa por ID
    @DeleteMapping("{id}/inativar")
    public ResponseEntity<Void> inativarEmpresa(@PathVariable Long id){
        empresaService.inativarEmpresa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Buscar licensas sanitarias de uma empresa pelo ID
    @GetMapping("{id}/licensasSanitarias")
    public ResponseEntity<List<LicensaSanitariaResponseDto>> getAllLicensasSanitarias(@PathVariable Long id){
        return ResponseEntity.ok().body(empresaService.listarLicensasSanitarias(id));
    }

    //Criar licensa sanitaria para uma empresa
    @PostMapping("{id}/licensasSanitarias")
    public ResponseEntity<LicensaSanitariaResponseDto> saveLincensaSanitaria(@PathVariable Long id, @RequestBody LicensaSanitariaCadastroDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveLicensaSanitaria(id,payload));
    }
}
