package mscitizen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.UserRole;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.profiles;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
