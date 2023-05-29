package mscitizen.repository;

import mscitizen.entity.Citizen;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByCpf(String cpf);

    List<Citizen> findByFullNameIgnoreCaseContaining(String fullName);

    List<Citizen> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
}
