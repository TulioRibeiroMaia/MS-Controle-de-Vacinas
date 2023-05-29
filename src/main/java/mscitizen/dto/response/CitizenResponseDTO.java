package mscitizen.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenResponseDTO {
    private Long id;

    private String cpf;

    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String cns;
}
