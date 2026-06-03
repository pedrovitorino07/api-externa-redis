package vitorino.pedro.consulta_api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CepDTO implements Serializable {

    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ddd;
    private Boolean erro;
}
