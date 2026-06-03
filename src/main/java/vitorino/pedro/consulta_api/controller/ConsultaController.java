package vitorino.pedro.consulta_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vitorino.pedro.consulta_api.service.ConsultaService;
import vitorino.pedro.consulta_api.dto.CepDTO;

@Tag(name = "Consulta CEP", description = "API responsável por consultar CEPs utilizando ViaCEP")
@RestController
@RequestMapping("/consulta-cep")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Operation(summary = "Consultar CEP", description = "Realiza consulta de CEP utilizando a API ViaCEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CEP encontrado"),
            @ApiResponse(responseCode = "400", description = "CEP inválido"),
            @ApiResponse(responseCode = "404", description = "CEP não encontrado"),
            @ApiResponse(responseCode = "504", description = "Timeout da API externa")
    })
    @GetMapping("{cep}")
    public CepDTO consulta(@PathVariable("cep") String cep) {
        return consultaService.buscarCep(cep);
    }
}
