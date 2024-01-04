package pl.sawiak_company.sok.code_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface CodeGroupRepository extends JpaRepository<CodeGroup, Integer> {
    Optional<CodeGroup> findById(Integer id);
}
