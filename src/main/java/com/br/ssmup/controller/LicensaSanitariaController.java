package com.br.ssmup.controller;

import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.dto.PageDto;
import com.br.ssmup.service.LicensaSanitariaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/licensas")
public class LicensaSanitariaController {

    private final LicensaSanitariaService licensaSanitariaService;

    public LicensaSanitariaController(LicensaSanitariaService licensaSanitariaService) {
        this.licensaSanitariaService = licensaSanitariaService;
    }

    @GetMapping
    public ResponseEntity<List<LicensaSanitariaResponseDto>> getAllLicensas(){
        return ResponseEntity.ok(licensaSanitariaService.buscarLicensasSanitaria());
    }

    @GetMapping("pagination")
    public ResponseEntity<PageDto<LicensaSanitariaResponseDto>> getAllLicensasPage(@PageableDefault(page = 0, size = 10, sort = "numControle", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(licensaSanitariaService.buscarLicensasSanitariaPagable(pageable));
    }

}
