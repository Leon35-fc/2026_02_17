package fabiocarlino.u5l12.controllers;

import fabiocarlino.u5l12.entities.Viaggio;
import fabiocarlino.u5l12.payloads.ViaggiDTO;
import fabiocarlino.u5l12.services.ViaggiService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {
    private final ViaggiService viaggiService;

    @Autowired
    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    //POST su http://localhost:3001/viaggi + payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio creaViaggio(@RequestBody @Validated ViaggiDTO viaggiDTO, BindingResult validationResult) {

        //CONTROLLO DEGLI ERRORI
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException((Throwable) errorsList);
        } else {
            return this.viaggiService.save(viaggiDTO);
        }

    }
}
