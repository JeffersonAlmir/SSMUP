/*
    Descomente o bloco de codigo para popular a tabela de empresas com o arquivo que esta em resource/data, escolha
    um deles.
*/

//package com.br.ssmup.utils;
//
//import com.br.ssmup.dto.EmpresaCadastroDto;
//import com.br.ssmup.service.EmpresaService;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.List;
//
//@Component
//public class EmpresaDataLoader implements CommandLineRunner {
//
//    private final EmpresaService empresaService;
//    //converte Json em Objeto
//    private final ObjectMapper objectMapper;
//
//    public EmpresaDataLoader(EmpresaService empresaService, ObjectMapper objectMapper) {
//        this.empresaService = empresaService;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void run(String... args) {
//
//        try (InputStream inputStream = getClass().getResourceAsStream("/data/empresas_100.json")) {
//
//            if (inputStream == null) {
//                System.out.println("Arquivo não encontrado em src/main/resources/data");
//                return;
//            }
//
//            List<EmpresaCadastroDto> empresas = objectMapper.readValue(
//                    inputStream,
//                    new TypeReference<List<EmpresaCadastroDto>>() {}
//            );
//
//            for (EmpresaCadastroDto dto : empresas) {
//                try {
//                    empresaService.saveEmpresa(dto);
//                } catch (Exception e) {
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Erro ao ler arquivo JSON de empresas: " + e.getMessage());
//        }
//    }
//}
