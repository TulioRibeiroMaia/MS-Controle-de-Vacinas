package mscitizen.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenRequestDTO {

    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthdate;

    @Size(min = 15, max = 15, message = "Tamanho inválido: o número do CNS deve conter 15 dígitos")
    private String cns;
}
