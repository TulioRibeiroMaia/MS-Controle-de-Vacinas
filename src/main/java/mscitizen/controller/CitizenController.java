package mscitizen.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mscitizen.dto.request.CitizenRequestDTO;
import mscitizen.dto.response.CitizenResponseDTO;
import mscitizen.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidadaos")
public class CitizenController {

    @Autowired
    private CitizenService service;

    //cadastra novos cidadãos
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @PostMapping
    @Transactional
    public ResponseEntity<CitizenResponseDTO> saveCitizen(@RequestBody @Valid CitizenRequestDTO body) {
        CitizenResponseDTO citizen = this.service.save(body);
        return new ResponseEntity<> (citizen, HttpStatus.CREATED);
    }



    //lista os cidadãos com filtro por nome e idade.
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<CitizenResponseDTO>> getCitizens(@RequestParam(name = "nome", required = false) String fullName,
                                                                @RequestParam(name = "data-inicial", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate startDate,
                                                                @RequestParam(name = "data-final", required = false) @DateTimeFormat(pattern = "ddMMyyyy") LocalDate endDate) {
        List<CitizenResponseDTO> citizen = this.service.getCitizens(fullName, startDate, endDate);
        return ResponseEntity.ok(citizen);
    }

    //procura o cidadão pelo seu cpf
    @GetMapping("/{cpf}")
    public ResponseEntity<CitizenResponseDTO> searchCitizen(@PathVariable String cpf){
        CitizenResponseDTO citizen = this.service.searchCitizen(cpf);
        return ResponseEntity.ok(citizen);
    }

    //atualiza os dados de um cidadão
//    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<CitizenResponseDTO> updateCitizen(@PathVariable String cpf, @RequestBody @Valid CitizenRequestDTO body) {
        CitizenResponseDTO citizen = this.service.updateCitizen(cpf, body);
        return ResponseEntity.ok(citizen);
    }

    //deleta um cidadão
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<?> deleteCitizen(@PathVariable String cpf) {
        this.service.deleteCitizen(cpf);
        return ResponseEntity.noContent().build();
    }
}
