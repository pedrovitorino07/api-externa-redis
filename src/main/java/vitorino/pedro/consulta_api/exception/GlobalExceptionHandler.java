package vitorino.pedro.consulta_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vitorino.pedro.consulta_api.dto.ErrorResponseDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CepInexistente.class)
    public ResponseEntity<ErrorResponseDTO> handleCepInexistente(CepInexistente e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CepInvalido.class)
    public ResponseEntity<ErrorResponseDTO> handleCepInvalido(CepInvalido e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TimeoutExterno.class)
    public ResponseEntity<ErrorResponseDTO> handleTimeoutExterno(TimeoutExterno e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.GATEWAY_TIMEOUT.value(),
                HttpStatus.GATEWAY_TIMEOUT.name(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(error);
    }

    @ExceptionHandler(FalhaInterna.class)
    public ResponseEntity<ErrorResponseDTO> handleFalhaInterna(FalhaInterna e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "Erro interno inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
