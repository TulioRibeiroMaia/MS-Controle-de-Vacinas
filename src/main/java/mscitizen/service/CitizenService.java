package mscitizen.service;

import mscitizen.dto.request.CitizenRequestDTO;
import mscitizen.dto.response.CitizenResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface CitizenService {

    CitizenResponseDTO save(CitizenRequestDTO body);

    List<CitizenResponseDTO> getCitizens(String fullName, LocalDate startDate, LocalDate endDate);

    CitizenResponseDTO searchCitizen(String cpf);

    CitizenResponseDTO updateCitizen(String cpf, CitizenRequestDTO body);

    void deleteCitizen(String cpf);
}
