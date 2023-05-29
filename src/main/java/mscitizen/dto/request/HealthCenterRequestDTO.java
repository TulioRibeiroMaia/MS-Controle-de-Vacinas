package mscitizen.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCenterRequestDTO {

    @NotEmpty
    private String cnes;

    @NotEmpty
    private String name;

    @NotEmpty
    private String city;

    @NotNull(message = "Insira uma sigla válida de UF")
    private State state;
}
