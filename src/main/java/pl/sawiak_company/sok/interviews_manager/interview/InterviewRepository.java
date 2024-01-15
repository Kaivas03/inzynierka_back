package pl.sawiak_company.sok.interviews_manager.interview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    Optional<Interview> findById(Integer id);

    List<Interview> findAllBySociologicalProject(SociologicalProject sociologicalProject);
}
