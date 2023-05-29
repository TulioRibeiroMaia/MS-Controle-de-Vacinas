package mscitizen.service.implementation;

import mscitizen.dto.request.HealthCenterRequestDTO;
import mscitizen.dto.response.HealthCenterResponseDTO;
import mscitizen.entity.HealthCenter;
import mscitizen.enums.State;
import mscitizen.exceptions.ResourceNotFoundException;
import mscitizen.repository.HealthCenterRepository;
import mscitizen.service.HealthCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HealthCenterImplementation implements HealthCenterService {

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HealthCenterResponseDTO save(HealthCenterRequestDTO body) {
        HealthCenter healthCenter = modelMapper.map(body, HealthCenter.class);
        HealthCenter savedHealthCenter = this.healthCenterRepository.save(healthCenter);
        return modelMapper.map(savedHealthCenter, HealthCenterResponseDTO.class);
    }

    @Override
    public List<HealthCenterResponseDTO> getHealthCenters(String name, String stateStr, String city) {
        List<HealthCenter> healthCenters;

        if (name != null) {
            healthCenters = this.healthCenterRepository.findByNameIgnoreCaseContaining(name);
        } else if (stateStr != null) {
            State state = State.valueOf(stateStr);
            healthCenters = this.healthCenterRepository.findByState(state);
        } else if (city != null) {
            healthCenters = this.healthCenterRepository.findByCityIgnoreCaseContaining(city);
        } else {
            healthCenters = this.healthCenterRepository.findAll();
        }

        return healthCenters
                .stream()
                .map(healthCenter -> modelMapper
                        .map(healthCenter, HealthCenterResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HealthCenterResponseDTO searchHealthCenter(String cnes) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (healthCenter.isPresent()) {
            return modelMapper.map(healthCenter.get(), HealthCenterResponseDTO.class);
        }

        throw new ResourceNotFoundException("CNES " + cnes);
    }

    @Override
    public HealthCenterResponseDTO updateHealthCenter(String cnes, HealthCenterRequestDTO body) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (healthCenter.isPresent()) {
            HealthCenter updatedHealthCenter = modelMapper.map(body, HealthCenter.class);
            updatedHealthCenter.setId(healthCenter.get().getId());
            this.healthCenterRepository.save(updatedHealthCenter);

            return modelMapper.map(updatedHealthCenter, HealthCenterResponseDTO.class);
        }

        throw new ResourceNotFoundException("CNES " + cnes);
    }

    @Override
    public void deleteHealthCenter(String cnes) {
        Optional<HealthCenter> healthCenter = this.healthCenterRepository.findByCnes(cnes);

        if (!healthCenter.isPresent()) {
            throw new ResourceNotFoundException("CNES " + cnes);
        }

        this.healthCenterRepository.deleteById(healthCenter.get().getId());
    }
}
