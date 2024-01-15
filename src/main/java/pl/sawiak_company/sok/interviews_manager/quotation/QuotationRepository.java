package pl.sawiak_company.sok.interviews_manager.quotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
    Optional<Quotation> findById(Integer id);
}
