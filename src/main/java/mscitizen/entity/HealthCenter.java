package mscitizen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.State;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "HealthCenter")
public class HealthCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cnes", unique = true)
    private String cnes;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "healthCenter", cascade = CascadeType.ALL)
    @Column(name = "employees")
    private List<Employee> employees;
}
