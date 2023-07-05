package mscitizen.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mscitizen.dto.request.VaccineRequestDTO;
import mscitizen.dto.response.VaccineResponseDTO;
import mscitizen.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VaccineController {

    @Autowired
    private VaccineService service;

    //cadastra novas vacinas
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<VaccineResponseDTO> saveVaccine(@RequestBody @Valid VaccineRequestDTO body) {
        VaccineResponseDTO vaccine = this.service.save(body);
        return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
    }

    //lista as vacinas pelo número do lote e ordena pela validade.
    @GetMapping
    public ResponseEntity<List<VaccineResponseDTO>> getVaccines(@RequestParam(name = "lote", required = false) String lotNumber,
                                                                @RequestParam(name = "ordenar-validade", required = false) Boolean sortExpDate) {
        List<VaccineResponseDTO> vaccine = this.service.getVaccines(lotNumber, sortExpDate);
        return ResponseEntity.ok(vaccine);
    }

    //procura a vacina pelo id
    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponseDTO> searchVaccine(@PathVariable Long id){
        VaccineResponseDTO vaccine = this.service.searchVaccine(id);
        return ResponseEntity.ok(vaccine);
    }

    //atualiza os dados de uma vacina referenciado ao id
   @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateVaccine(@PathVariable Long id, @RequestBody @Valid VaccineRequestDTO body) {
        VaccineResponseDTO vaccine = this.service.updateVaccine(id, body);
        return ResponseEntity.ok(vaccine);
    }

    //deleta uma vacina
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteVaccine(@PathVariable Long id) {
        this.service.deleteVaccine(id);
        return ResponseEntity.ok().build();
    }
}