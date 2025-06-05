package br.com.valeway.router.transportation.repository;

import br.com.valeway.router.transportation.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
