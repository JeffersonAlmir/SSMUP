package com.br.ssmup.service;

import com.br.ssmup.dto.EmpresaResponseDto;
import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.entities.Empresa;
import com.br.ssmup.entities.LicensaSanitaria;
import com.br.ssmup.enums.RiscoSanitario;
import com.br.ssmup.mapper.EmpresaMapper;
import com.br.ssmup.mapper.LicensaSanitariaMapper;
import com.br.ssmup.repository.EmpresaRepository;
import com.br.ssmup.repository.LicensaSanitariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LicensaSanitariaService {

    private final LicensaSanitariaRepository licensaSanitariaRepository;
    private final LicensaSanitariaMapper licensaSanitariaMapper;
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final GeradorPdfService geradorPdfService;


    public LicensaSanitariaService(LicensaSanitariaRepository licensaSanitariaRepository, LicensaSanitariaMapper licensaSanitariaMapper, EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, GeradorPdfService geradorPdfService) {
        this.licensaSanitariaRepository = licensaSanitariaRepository;
        this.licensaSanitariaMapper = licensaSanitariaMapper;
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.geradorPdfService = geradorPdfService;
    }

    public List<LicensaSanitariaResponseDto> buscarLicensasSanitaria() {
        return licensaSanitariaRepository.findAll().stream()
                .map(licensaSanitariaMapper::toResponse)
                .toList();
    }

    public Page<LicensaSanitariaResponseDto> buscarLicensasSanitariaPagable(Pageable pageable){
        return licensaSanitariaRepository.findAll(pageable).map(licensaSanitariaMapper::toResponse);
    }

    public LicensaSanitariaResponseDto buscarLicencaSanitariaByNumControle(String numControle){

        LicensaSanitaria entity = licensaSanitariaRepository.findByNumControle(numControle)
                .orElseThrow(()-> new RuntimeException("Licença sanitária não encontrada"));
        return licensaSanitariaMapper.toResponse(entity);
    }

    public byte[] emitirAlvara(Long idEmpresa){

        Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        if(empresa.getCnaePrincipal() == null){
            throw new RuntimeException("Empresa sem CNAE vinculado. Atualize o cadastro.");
        }

        RiscoSanitario riscoSanitario = empresa.getCnaePrincipal().getRisco();

        if(riscoSanitario == RiscoSanitario.RISCO_III_ALTO){
            if(!empresa.isInspecao()){
                throw new RuntimeException("RISCO_III_ALTO_DETECTADO");
            }
        }

        LicensaSanitaria licensaParaImprimir = licensaSanitariaRepository
                .findFirstByEmpresaIdAndStatusTrue(idEmpresa)
                .orElse(null);

        if (licensaParaImprimir == null) {
            LicensaSanitaria novaLicensa = new LicensaSanitaria();
            novaLicensa.setEmpresa(empresa);
            novaLicensa.setNumControle(gerarNumeroControle());
            novaLicensa.setStatus(true);

            licensaParaImprimir = licensaSanitariaRepository.save(novaLicensa);
        }

        EmpresaResponseDto empresaDto = empresaMapper.toResponse(empresa);
        LicensaSanitariaResponseDto licensaDto = licensaSanitariaMapper.toResponse(licensaParaImprimir);

        return geradorPdfService.gerarLicensaSanitariaPdf(empresaDto, licensaDto);
    }

    private String gerarNumeroControle() {
        return LocalDateTime.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}