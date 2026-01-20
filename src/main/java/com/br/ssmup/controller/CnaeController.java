package com.br.ssmup.controller;

import com.br.ssmup.entities.Cnae;
import com.br.ssmup.repository.CnaeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/cnaes")
public class CnaeController {
    private final CnaeRepository cnaeRepository;

    public CnaeController(CnaeRepository cnaeRepository) {
        this.cnaeRepository = cnaeRepository;
    }

    @GetMapping
    public List<Cnae> listar(@RequestParam(required = false) String busca) {
        if (busca != null && !busca.isBlank()) {
            return cnaeRepository.buscarPorCodigoOuDescricao(busca);
        }
        return cnaeRepository.findAll();
    }
}
