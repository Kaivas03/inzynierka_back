package pl.sawiak_company.sok.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface CodeRepository extends JpaRepository<Code, Integer> {
    Optional<Code> findById(Integer id);

    List<Code> findAllBySociologicalProject(SociologicalProject sociologicalProject);
}
