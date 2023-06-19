package mscitizen.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mscitizen.dto.request.HealthCenterRequestDTO;
import mscitizen.dto.response.HealthCenterResponseDTO;
import mscitizen.service.HealthCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class HealthCenterController {

    @Autowired
    private HealthCenterService service;

    //cadastra novos postos de saúde
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<HealthCenterResponseDTO> saveHealthCenter(@RequestBody @Valid HealthCenterRequestDTO body) {
        HealthCenterResponseDTO healthCenter = this.service.save(body);
        return new ResponseEntity<>(healthCenter, HttpStatus.CREATED);
    }

    //lista os postos por nome, estado e cidade.
    @GetMapping
    public ResponseEntity<List<HealthCenterResponseDTO>> getHealthCenters(@RequestParam(name = "nome", required = false) String name,
                                                                          @RequestParam(name = "uf", required = false) String state,
                                                                          @RequestParam(name = "cidade", required = false) String city) {
        List<HealthCenterResponseDTO> healthCenter = this.service.getHealthCenters(name, state, city);
        return ResponseEntity.ok(healthCenter);
    }

    //procura o posto pelo cnes
    @GetMapping("/{cnes}")
    public ResponseEntity<HealthCenterResponseDTO> searchHealthCenter(@PathVariable String cnes){
        HealthCenterResponseDTO healthCenter = this.service.searchHealthCenter(cnes);
        return ResponseEntity.ok(healthCenter);
    }

    //atualiza os dados de um posto de saúde
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cnes}")
    @Transactional
    public ResponseEntity<HealthCenterResponseDTO> updateHealthCenter(@PathVariable String cnes, @RequestBody @Valid HealthCenterRequestDTO body) {
        HealthCenterResponseDTO healthCenter = this.service.updateHealthCenter(cnes, body);
        return ResponseEntity.ok(healthCenter);
    }

    //deleta um posto de saúde
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cnes}")
    @Transactional
    public ResponseEntity<?> deleteHealthCenter(@PathVariable String cnes) {
        this.service.deleteHealthCenter(cnes);
        return ResponseEntity.noContent().build();
    }
}
