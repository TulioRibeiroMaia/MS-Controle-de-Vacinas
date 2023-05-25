package mscitizen.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.VaccineName;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRequestDTO {

    @NotNull(message = "Nome inválido: verifique a lista de vacinas ofertadas pelo SUS")
    private VaccineName vaccineName;

    @NotEmpty
    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate expirationDate;

    @NotEmpty
    private String lotNumber;
}
