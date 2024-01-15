package pl.sawiak_company.sok.project_mind_map.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findById(Integer id);

    List<Question> findAllByHypothesis(Hypothesis hypothesis);
}
