package pl.sawiak_company.sok.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findById(Integer id);
}
