package mscitizen.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.UserRole;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    @CPF
    @NotEmpty
    private String cpf;

    @NotEmpty
    private String fullName;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthdate;

    @NotNull
    private UserRole userRole;
}
