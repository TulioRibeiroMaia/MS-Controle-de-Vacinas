package mscitizen.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
