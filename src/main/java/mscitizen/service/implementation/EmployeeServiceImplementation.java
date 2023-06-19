package mscitizen.service.implementation;

import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.entity.Employee;
import mscitizen.exceptions.ResourceNotFoundException;
import mscitizen.repository.EmployeeRepository;
import mscitizen.repository.HealthCenterRepository;
import mscitizen.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO body) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByCpf(body.getCpf());
        if (employeeOptional.isPresent()){
            throw new IllegalArgumentException("Funcionário com o cpf " + body.getCpf() + " já cadastrado!");
        }
        Employee employee = modelMapper.map(body, Employee.class);
        Employee savedEmployee = this.employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeResponseDTO.class);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees(String fullName) {
        List<Employee> employees;

        if (fullName != null) {
            employees = this.employeeRepository.findByFullNameIgnoreCaseContaining(fullName);
        } else {
            employees = this.employeeRepository.findAll();
        }

        return employees
                .stream()
                .map(employee -> modelMapper
                        .map(employee, EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO searchEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            return modelMapper.map(employee.get(), EmployeeResponseDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String cpf, EmployeeRequestDTO body) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            Employee updatedEmployee = modelMapper.map(body, Employee.class);
            updatedEmployee.setId(employee.get().getId());
            this.employeeRepository.save(updatedEmployee);

            return modelMapper.map(updatedEmployee, EmployeeResponseDTO.class);
        }

        throw new ResourceNotFoundException("CPF " + cpf);
    }

    @Override
    public void deleteEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (!employee.isPresent()) {
            throw new ResourceNotFoundException("CPF " + cpf);
        }

        this.employeeRepository.deleteById(employee.get().getId());
    }
}
