package mscitizen.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.VaccineName;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponseDTO {

    private Long id;

    private VaccineName vaccineName;

    private String manufacturer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    private String lotNumber;
}
