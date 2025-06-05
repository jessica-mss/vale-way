package br.com.valeway.router.transportation.controller;

import br.com.valeway.router.transportation.domain.Company;
import br.com.valeway.router.transportation.domain.dto.CompanyRequestDTO;
import br.com.valeway.router.transportation.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@PreAuthorize("hasRole('COMPANY')")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Company> criar(@RequestBody @Valid CompanyRequestDTO dto) {
        Company createdCompany = service.create(dto);

        URI location = URI.create("/company/" + createdCompany.getId());

        return ResponseEntity
                .created(location)
                .body(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<Company>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> atualizar(@PathVariable Long id, @RequestBody @Valid CompanyRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

