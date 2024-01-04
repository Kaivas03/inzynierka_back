package pl.sawiak_company.sok.interview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    Optional<Interview> findById(Integer id);
}
