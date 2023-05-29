package mscitizen.repository;

import mscitizen.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findById(Long id);

    List<Vaccine> findByLotNumberIgnoreCase(String lotNumber);
}
