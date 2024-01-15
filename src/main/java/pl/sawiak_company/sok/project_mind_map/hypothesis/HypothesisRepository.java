package pl.sawiak_company.sok.project_mind_map.hypothesis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface HypothesisRepository extends JpaRepository<Hypothesis, Integer> {
    Optional<Hypothesis> findById(Integer id);

    List<Hypothesis> findAllBySociologicalProject(SociologicalProject sociologicalProject);
}
