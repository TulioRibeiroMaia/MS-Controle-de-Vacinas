package mscitizen.repository;

import mscitizen.entity.HealthCenter;
import mscitizen.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthCenterRepository extends JpaRepository<HealthCenter, Long> {

    Optional<HealthCenter> findByCnes(String cnes);

    List<HealthCenter> findByNameIgnoreCaseContaining(String name);

    List<HealthCenter> findByCityIgnoreCaseContaining(String city);

    List<HealthCenter> findByState(State state);
}
