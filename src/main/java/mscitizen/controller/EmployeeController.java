package mscitizen.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //cadastra novos funcionários
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody @Valid EmployeeRequestDTO body) {
        EmployeeResponseDTO employee = this.service.save(body);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //lista os empregados com filtro por nome.
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(@RequestParam(name = "nome", required = false) String fullName) {
        List<EmployeeResponseDTO> employee = this.service.getEmployees(fullName);
        return ResponseEntity.ok(employee);
    }

    //procura o empregado pelo seu cpf
    @PreAuthorize("hasAnyRole('ADMIN','FUNCIONARIO')")
    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeResponseDTO> searchEmployee(@PathVariable String cpf){
        EmployeeResponseDTO employee = this.service.searchEmployee(cpf);
        return ResponseEntity.ok(employee);
    }

    //atualiza os dados de um empregado
   @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable String cpf, @RequestBody @Valid EmployeeRequestDTO body) {
        EmployeeResponseDTO employee = this.service.updateEmployee(cpf, body);
        return ResponseEntity.ok(employee);
    }

    //deleta um empregado
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<?> deleteEmployee(@PathVariable String cpf) {
        this.service.deleteEmployee(cpf);
        return ResponseEntity.noContent().build();
    }
}
