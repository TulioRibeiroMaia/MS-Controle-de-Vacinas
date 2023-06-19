package mscitizen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.UserRole;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Citizen")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "fullName")
    public String fullName;

    @Column(name = "birthdate", columnDefinition = "TIMESTAMP")
    public LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    public UserRole userRole = UserRole.USUARIO;

    @Column(name = "cns")
    private String cns;

}
