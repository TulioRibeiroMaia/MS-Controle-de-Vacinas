package mscitizen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.UserRole;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    private UserRole userRole;

//    @Override
//    public String getAuthority() {
//        return String.valueOf(userRole);
//    }
}