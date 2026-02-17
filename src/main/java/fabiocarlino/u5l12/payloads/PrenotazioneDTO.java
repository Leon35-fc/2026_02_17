package fabiocarlino.u5l12.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "l'id del viaggio è obbligatorio")
        UUID viaggioId,

        @NotNull(message = "l'id del dipendente è obbligatorio")
        UUID dipendenteId,
        String note
) {
}
