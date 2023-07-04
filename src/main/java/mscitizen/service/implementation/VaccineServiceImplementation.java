package mscitizen.service.implementation;

import mscitizen.dto.request.VaccineRequestDTO;
import mscitizen.dto.response.VaccineResponseDTO;
import mscitizen.entity.Vaccine;
import mscitizen.exceptions.LotNumberAlreadyExistsException;
import mscitizen.exceptions.VaccineIDDoesntExistsException;
import mscitizen.exceptions.VaccineNameAndManufacturerAlreadyExistsException;
import mscitizen.repository.VaccineRepository;
import mscitizen.service.VaccineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImplementation implements VaccineService {


    private VaccineRepository vaccineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VaccineResponseDTO save(VaccineRequestDTO body) {
        validateVaccineNameAndManufacturer(body);
        validateLotNumber(body);
        Vaccine vaccine = modelMapper.map(body, Vaccine.class);
       Vaccine savedVaccine = this.vaccineRepository.save(vaccine);
       return modelMapper.map(savedVaccine,VaccineResponseDTO.class);
    }
    @Override
    public List<VaccineResponseDTO> getVaccines(String lotNumber, Boolean sortExpDate) {
        List<Vaccine> vaccines;

        if (lotNumber == null) {
            vaccines = this.vaccineRepository.findAll();
        } else {
            vaccines = this.vaccineRepository.findByLotNumberIgnoreCase(lotNumber);
        }

        if (Boolean.TRUE.equals(sortExpDate)) {
            vaccines.sort(Comparator.comparing(Vaccine::getExpirationDate));
        }

        return vaccines
                .stream()
                .map(vaccine -> modelMapper
                        .map(vaccine, VaccineResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VaccineResponseDTO searchVaccine(Long id) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (vaccine.isPresent()) {
            return modelMapper.map(vaccine.get(), VaccineResponseDTO.class);
        }

        throw new VaccineIDDoesntExistsException(id);
    }

    @Override
    public VaccineResponseDTO updateVaccine(Long id, VaccineRequestDTO body) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (vaccine.isPresent()) {
            Vaccine updatedVaccine = modelMapper.map(body, Vaccine.class);
            updatedVaccine.setId(id);
            this.vaccineRepository.save(updatedVaccine);

            return modelMapper.map(updatedVaccine, VaccineResponseDTO.class);
        }

        throw new VaccineIDDoesntExistsException(id);
    }

    @Override
    public void deleteVaccine(Long id) {
        Optional<Vaccine> vaccine = this.vaccineRepository.findById(id);

        if (!vaccine.isPresent()) {
            throw new VaccineIDDoesntExistsException(id);
        }

        this.vaccineRepository.deleteById(id);
    }

    private void validateLotNumber(VaccineRequestDTO body) {
        Optional<Vaccine> vaccineLotNumber = this.vaccineRepository.findByLotNumber(body.getLotNumber());
        if (vaccineLotNumber.isPresent()) {
            throw new LotNumberAlreadyExistsException(body.getLotNumber());
        }
    }

    private void validateVaccineNameAndManufacturer(VaccineRequestDTO body) {
        Optional<Vaccine> vaccineNameAndManufacturer = this.vaccineRepository.findByVaccineNameAndManufacturer(body.getVaccineName(), body.getManufacturer());
        if ( vaccineNameAndManufacturer.isPresent()) {
            throw new VaccineNameAndManufacturerAlreadyExistsException(body.getVaccineName().getValue(), body.getManufacturer());
        }
    }
}
