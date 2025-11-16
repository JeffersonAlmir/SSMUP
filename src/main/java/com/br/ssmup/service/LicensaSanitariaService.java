package com.br.ssmup.service;

import com.br.ssmup.dto.LicensaSanitariaResponseDto;
import com.br.ssmup.dto.PageDto;
import com.br.ssmup.mapper.LicensaSanitariaMapper;
import com.br.ssmup.repository.LicensaSanitariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicensaSanitariaService {

    private final LicensaSanitariaRepository licensaSanitariaRepository;
    private final LicensaSanitariaMapper licensaSanitariaMapper;

    public LicensaSanitariaService(LicensaSanitariaRepository licensaSanitariaRepository, LicensaSanitariaMapper licensaSanitariaMapper) {
        this.licensaSanitariaRepository = licensaSanitariaRepository;
        this.licensaSanitariaMapper = licensaSanitariaMapper;
    }

    public List<LicensaSanitariaResponseDto> buscarLicensasSanitaria() {
        return licensaSanitariaRepository.findAll().stream()
                .map(licensaSanitariaMapper::toResponse)
                .toList();
    }

    public PageDto<LicensaSanitariaResponseDto> buscarLicensasSanitariaPagable(Pageable pageable){
        Page<LicensaSanitariaResponseDto> page =  licensaSanitariaRepository.findAll(pageable).map(licensaSanitariaMapper::toResponse);
        return PageDto.of(page);
    }

}