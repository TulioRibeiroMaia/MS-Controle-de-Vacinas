package mscitizen.service;

import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO save(EmployeeRequestDTO body);

    List<EmployeeResponseDTO> getEmployees(String name);

    EmployeeResponseDTO searchEmployee(String cpf);

    EmployeeResponseDTO updateEmployee(String cpf, EmployeeRequestDTO body);

    void deleteEmployee(String cpf);
}
