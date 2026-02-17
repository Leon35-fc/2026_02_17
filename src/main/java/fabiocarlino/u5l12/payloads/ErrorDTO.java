package fabiocarlino.u5l12.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message, LocalDateTime timestamp
) {
}
