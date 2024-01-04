package pl.sawiak_company.sok.hypothesis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface HypothesisRepository extends JpaRepository<Hypothesis, Integer> {
    Optional<Hypothesis> findById(Integer id);
}
