package pl.sawiak_company.sok.sociological_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface SociologicalProjectRepository extends JpaRepository<SociologicalProject, Integer> {
    Optional<SociologicalProject> findById(Integer id);
}
