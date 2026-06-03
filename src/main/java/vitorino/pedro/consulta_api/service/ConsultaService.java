package vitorino.pedro.consulta_api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import vitorino.pedro.consulta_api.dto.CepDTO;
import vitorino.pedro.consulta_api.exception.CepInexistente;
import vitorino.pedro.consulta_api.exception.CepInvalido;
import vitorino.pedro.consulta_api.exception.FalhaInterna;
import vitorino.pedro.consulta_api.exception.TimeoutExterno;

@Slf4j
@Service
public class ConsultaService {

    private final RestTemplate restTemplate;

    @Value("${viacep.url}")
    private String viaCepUrl;

    public ConsultaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "cep", key = "#cep", unless = "#result == null")
    public CepDTO buscarCep(String cep) {

        validarCep(cep);

        log.info("Consultando CEP: {}", cep);

        try {

            log.info("Realizando chamada para ViaCEP. CEP: {}", cep);

            ResponseEntity<CepDTO> response = restTemplate.getForEntity(String.format("%s/%s/json/", viaCepUrl, cep), CepDTO.class);

            CepDTO cepDTO = response.getBody();

            if (cepDTO == null) {
                throw new FalhaInterna("Falha ao processar resposta do ViaCEP");
            }

            if (Boolean.TRUE.equals(cepDTO.getErro())) {
                throw new CepInexistente("CEP não encontrado");
            }

            log.info("CEP encontrado com sucesso. CEP: {}", cep);

            return cepDTO;

        } catch (ResourceAccessException e) {

            log.error("Timeout ao consultar ViaCEP. CEP: {}", cep, e);

            throw new TimeoutExterno("O serviço ViaCEP demorou para responder");

        } catch (CepInexistente e) {

            log.warn("CEP não encontrado. CEP: {}", cep);

            throw e;

        } catch (Exception e) {

            log.error("Erro inesperado ao consultar CEP: {}", cep, e);

            throw new FalhaInterna("Erro interno ao consultar o ViaCEP");
        }
    }

    private void validarCep(String cep) {

        if (cep == null || cep.isBlank()) {

            log.warn("CEP vazio informado");

            throw new CepInvalido("CEP não pode ser vazio");
        }

        if (!cep.matches("\\d{8}")) {

            log.warn("CEP inválido informado. CEP: {}", cep);

            throw new CepInvalido("CEP deve conter exatamente 8 números");
        }
    }
}