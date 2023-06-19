package mscitizen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.VaccineName;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vaccineName")
    @Enumerated(EnumType.STRING)
    private VaccineName vaccineName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufactureDate", columnDefinition = "TIMESTAMP")
    private LocalDate manufactureDate;

    @Column(name = "expirationDate", columnDefinition = "TIMESTAMP")
    private LocalDate expirationDate;

    @Column(name = "lotNumber")
    private String lotNumber;
}
