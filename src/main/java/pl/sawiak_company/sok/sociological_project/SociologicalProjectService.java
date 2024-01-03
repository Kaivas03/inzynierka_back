package pl.sawiak_company.sok.sociological_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;

import java.util.List;

@Service
public class SociologicalProjectService {
    @Autowired
    private SociologicalProjectRepository projectRepository;

    public SociologicalProject createProject(String name) {

        SociologicalProject project = SociologicalProject.builder()
                .name(name)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        projectRepository.save(project);
        return project;
    }

    public SociologicalProject getById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RequestException("No PROJECT within given Id: " + id, HttpStatus.BAD_REQUEST, "PROJECT_NOT_EXISTS"));
    }

    public List<SociologicalProject> getAll() {
        return projectRepository.findAll();
    }

    public SociologicalProject editProject(Integer id, String name) {
        SociologicalProject project = getById(id);

        project.setName(name);
        project.setEditionSignature(new EditionSignature());
        projectRepository.save(project);
        return project;
    }

    public void deleteProject(Integer id) {
        SociologicalProject project = getById(id);
        projectRepository.delete(project);
    }
}
