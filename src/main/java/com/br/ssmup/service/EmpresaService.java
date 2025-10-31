package com.br.ssmup.service;

import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.repository.EmpresaRepository;
import com.br.ssmup.repository.LicensaSanitariaRepository;
import com.br.ssmup.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final ResponsavelRepository responsavelRepository;
    private final LicensaSanitariaRepository licensaSanitariaRepository;

    public EmpresaService(EmpresaRepository empresaRepository, ResponsavelRepository responsavelRepository, LicensaSanitariaRepository licensaSanitariaRepository) {
        this.empresaRepository = empresaRepository;
        this.responsavelRepository = responsavelRepository;
        this.licensaSanitariaRepository = licensaSanitariaRepository;
    }

    public Empresa saveEmpresa(Empresa empresa) {
        empresa.adicionarEndereco(empresa.getEndereco());
        responsavelRepository.save(empresa.getResponsavel());
        return empresaRepository.save(empresa);
    }

    public LicensaSanitaria saveLicensaSanitaria(Long id, LicensaSanitaria licensa) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if(empresa == null) {
            throw new RuntimeException("Empresa not found");
        }
        licensa.setEmpresa(empresa);
        return licensaSanitariaRepository.save(licensa);
    }

    public List<Empresa>  findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public void  deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
