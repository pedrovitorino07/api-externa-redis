package vitorino.pedro.consulta_api.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        Integer status,
        String error,
        LocalDateTime timestamp
) {
}
