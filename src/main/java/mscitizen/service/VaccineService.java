package mscitizen.service;

import mscitizen.dto.request.VaccineRequestDTO;
import mscitizen.dto.response.VaccineResponseDTO;

import java.util.List;

public interface VaccineService {

    VaccineResponseDTO save(VaccineRequestDTO body);

    List<VaccineResponseDTO> getVaccines(String lotNumber, Boolean sortExpDate);

    VaccineResponseDTO searchVaccine(Long id);

    VaccineResponseDTO updateVaccine(Long id, VaccineRequestDTO body);

    void deleteVaccine(Long id);
}
