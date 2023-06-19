package mscitizen.repository;

import mscitizen.entity.Vaccine;
import mscitizen.enums.VaccineName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findById(Long id);

    List<Vaccine> findByLotNumberIgnoreCase(String lotNumber);

    Optional<Vaccine> findByVaccineNameAndManufacturer(VaccineName vaccineName, String manufacturer);

    Optional<Vaccine> findByLotNumber(String lotNumber);
}
