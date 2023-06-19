//package mscitizen.dto.request;
//
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class LoginRequestDTO {
//    @NotEmpty
//    private String email;
//    @NotEmpty
//    private String password;
//
//    public UsernamePasswordAuthenticationToken convert() {
//        return new UsernamePasswordAuthenticationToken(email, password);
//    }
//}
