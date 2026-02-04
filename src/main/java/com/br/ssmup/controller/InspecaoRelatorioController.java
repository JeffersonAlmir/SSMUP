package com.br.ssmup.controller;

import com.br.ssmup.dto.InspecaoRelatorioRequestDto;
import com.br.ssmup.dto.InspecaoRelatorioResponseDto;
import com.br.ssmup.service.InspecaoRelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/inspecoes")
public class InspecaoRelatorioController {

    private final InspecaoRelatorioService inspecaoRelatorioService;

    public InspecaoRelatorioController(InspecaoRelatorioService inspecaoRelatorioService) {
        this.inspecaoRelatorioService = inspecaoRelatorioService;
    }

    @GetMapping
    public ResponseEntity<List<InspecaoRelatorioResponseDto>> findAll(){
        return ResponseEntity.ok(inspecaoRelatorioService.listarInspecaoRelatorio());
    }

    @GetMapping("empresas/{id}")
    public ResponseEntity<List<InspecaoRelatorioResponseDto>> findAllByEmpresaId(@PathVariable Long id){
        return ResponseEntity.ok(inspecaoRelatorioService.listarInspecaoRelatorioByEmpresaId(id));
    }

    @PostMapping
    public ResponseEntity<InspecaoRelatorioResponseDto> postInspecaoRelatorio(@RequestBody @Valid InspecaoRelatorioRequestDto inspecaoRelatorioRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(inspecaoRelatorioService.salvarInspecaoRelatorio(inspecaoRelatorioRequestDto));
    }
}
