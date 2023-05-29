package mscitizen.service;

import mscitizen.dto.request.HealthCenterRequestDTO;
import mscitizen.dto.response.HealthCenterResponseDTO;

import java.util.List;

public interface HealthCenterService {

    HealthCenterResponseDTO save(HealthCenterRequestDTO body);

    List<HealthCenterResponseDTO> getHealthCenters(String name, String state, String city);

    HealthCenterResponseDTO searchHealthCenter(String cnes);

    HealthCenterResponseDTO updateHealthCenter(String cnes, HealthCenterRequestDTO body);

    void deleteHealthCenter(String cnes);
}