package mscitizen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.UserRole;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

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
    public UserRole userRole;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "HealthCenterID")
    private HealthCenter healthCenter;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "ProfileID")
    private List<Profile> profiles = new ArrayList<>();

    public List<Profile> getAuthorities() {
        return  this.profiles;
    }

    public String getPassword() {
        return this.password;
    }


    public String getUsername() {
        return this.email;
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Optional<Object> findByEmail(String email) { return Optional.ofNullable(this.email);
    }
}
