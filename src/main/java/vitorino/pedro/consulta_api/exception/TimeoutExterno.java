package vitorino.pedro.consulta_api.exception;

public class TimeoutExterno extends RuntimeException {
    public TimeoutExterno(String message) {
        super(message);
    }
}
