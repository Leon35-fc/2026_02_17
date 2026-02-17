package fabiocarlino.u5l12.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Il campo \"nome\" è obbligatorio.")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3-30 caratteri.")
        String nome,

        @NotBlank(message = "Il campo \"cognome\" è obbligatorio.")
        @Size(min = 3, max = 30, message = "Il cognome deve essere compreso tra 3-30 caratteri.")
        String cognome,

        @NotBlank(message = "Il campo \"username\" è obbligatorio.")
        @Size(min = 3, max = 30, message = "L'username deve essere compreso tra 3-30 caratteri.")
        String username,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 4, max = 16, message = "La password deve avere un numero di caratteri compreso tra 4-16")
        String password
) {
}
