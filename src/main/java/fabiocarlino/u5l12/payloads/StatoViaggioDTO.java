package fabiocarlino.u5l12.payloads;

import fabiocarlino.u5l12.entities.StatoViaggio;
import jakarta.validation.constraints.NotNull;

public record StatoViaggioDTO(
        @NotNull(message = "lo stato del viaggio è obbligatorio")
        StatoViaggio statoViaggio
) {
}
