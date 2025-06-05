package br.com.valeway.router.transportation.service;

import br.com.valeway.router.transportation.domain.Company;
import br.com.valeway.router.transportation.domain.dto.CompanyRequestDTO;
import br.com.valeway.router.transportation.exception.CompanyNotFoundException;
import br.com.valeway.router.transportation.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company create(CompanyRequestDTO dto) {
        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .cnpj(dto.getCnpj())
                .address(dto.getAddress())
                .contactEmail(dto.getContactEmail())
                .contactPhone(dto.getContactPhone())
                .contactPersonName(dto.getContactPersonName())
                .status(dto.getStatus())
                .build();
        return repository.save(company);
    }

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    public Company update(Long id, CompanyRequestDTO dto) {
        Company company = getById(id);
        company.setCompanyName(dto.getCompanyName());
        company.setCnpj(dto.getCnpj());
        company.setAddress(dto.getAddress());
        company.setContactEmail(dto.getContactEmail());
        company.setContactPhone(dto.getContactPhone());
        company.setContactPersonName(dto.getContactPersonName());
        company.setStatus(dto.getStatus());
        return repository.save(company);
    }


    public void delete(Long id) {
        Company Company = getById(id);
        repository.delete(Company);
    }
}
