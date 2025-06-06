package br.com.valeway.router.transportation.service;

import br.com.valeway.router.transportation.domain.Company;
import br.com.valeway.router.transportation.domain.Employee;
import br.com.valeway.router.transportation.domain.dto.EmployeeRequestDTO;
import br.com.valeway.router.transportation.exception.CompanyNotFoundException;
import br.com.valeway.router.transportation.exception.EmployeeNotFoundException;
import br.com.valeway.router.transportation.repository.CompanyRepository;
import br.com.valeway.router.transportation.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final CompanyRepository companyRepository;

    public Employee create(EmployeeRequestDTO dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        Employee employee = Employee.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .salary(dto.getSalary())
                .address(dto.getAddress())
                .registration(dto.getRegistration())
                .company(company)
                .build();

        return repository.save(employee);
    }

    public Employee update(Long id, EmployeeRequestDTO dto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado"));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Empresa não encontrada"));

        employee.setName(dto.getName());
        employee.setCpf(dto.getCpf());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setRegistration(dto.getRegistration());
        employee.setCompany(company);

        return repository.save(employee);
    }

    public void delete(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado"));
        repository.delete(employee);
    }

    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado"));
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}

