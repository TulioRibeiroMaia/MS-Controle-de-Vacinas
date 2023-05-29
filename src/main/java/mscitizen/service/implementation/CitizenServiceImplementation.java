package mscitizen.service.implementation;

import mscitizen.dto.request.CitizenRequestDTO;
import mscitizen.dto.response.CitizenResponseDTO;
import mscitizen.entity.Citizen;
import mscitizen.exceptions.ResourceNotFoundException;
import mscitizen.repository.CitizenRepository;
import mscitizen.repository.VaccineRepository;
import mscitizen.service.CitizenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImplementation implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CitizenResponseDTO save(CitizenRequestDTO body) {
        Citizen citizen = modelMapper.map(body, Citizen.class);
        Citizen savedCitizen = this.citizenRepository.save(citizen);
        return modelMapper.map(savedCitizen, CitizenResponseDTO.class);
    }

    @Override
    public List<CitizenResponseDTO> getCitizens(String fullName, LocalDate startDate, LocalDate endDate) {
        List<Citizen> citizens;

        if (fullName != null) {
            citizens = this.citizenRepository.findByFullNameIgnoreCaseContaining(fullName);
        } else if (startDate != null) {
            if (endDate != null) {
                citizens = this.citizenRepository.findByBirthDateBetween(startDate, endDate);
            } else {
                citizens = this.citizenRepository.findByBirthDateBetween(startDate, LocalDate.now());
            }
        } else {
            citizens = this.citizenRepository.findAll();
        }

        return citizens
                .stream()
                .map(citizen -> modelMapper
                        .map(citizen, CitizenResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CitizenResponseDTO searchCitizen(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            return modelMapper.map(citizen.get(), CitizenResponseDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public CitizenResponseDTO updateCitizen(String cpf, CitizenRequestDTO body) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (citizen.isPresent()) {
            Citizen updatedCitizen = modelMapper.map(body, Citizen.class);
            updatedCitizen.setId(citizen.get().getId());
            this.citizenRepository.save(updatedCitizen);

            return modelMapper.map(updatedCitizen, CitizenResponseDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public void deleteCitizen(String cpf) {
        Optional<Citizen> citizen = this.citizenRepository.findByCpf(cpf);

        if (!citizen.isPresent()) {
            throw new ResourceNotFoundException("CPF " + cpf);
        }

        this.citizenRepository.deleteById(citizen.get().getId());
    }
}
