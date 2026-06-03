package vitorino.pedro.consulta_api.exception;

public class CepInexistente extends RuntimeException {
    public CepInexistente(String message) {
        super(message);
    }
}
