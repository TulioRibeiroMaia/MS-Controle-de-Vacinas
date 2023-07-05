package mscitizen.service.implementation;

import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.entity.Employee;
import mscitizen.exceptions.CpfAlreadyExistsException;
import mscitizen.exceptions.CpfDoesntExistsException;
import mscitizen.repository.EmployeeRepository;
import mscitizen.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO body) {
        validateIfCPFExists(body);
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
                .toList();
    }

    @Override
    public EmployeeResponseDTO searchEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isPresent()) {
            return modelMapper.map(employee.get(), EmployeeResponseDTO.class);
        }

        throw new CpfDoesntExistsException(cpf);
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

        throw new CpfDoesntExistsException(cpf);
    }

    @Override
    public void deleteEmployee(String cpf) {
        Optional<Employee> employee = this.employeeRepository.findByCpf(cpf);

        if (employee.isEmpty()) {
            throw new CpfDoesntExistsException(cpf);
        }

        this.employeeRepository.deleteById(employee.get().getId());
    }

    private void validateIfCPFExists(EmployeeRequestDTO body) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByCpf(body.getCpf());
        if (employeeOptional.isPresent()){
            throw new CpfAlreadyExistsException(body.getCpf());
        }
    }
}
