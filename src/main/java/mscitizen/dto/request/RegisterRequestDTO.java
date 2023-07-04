package mscitizen.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.entity.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
}
