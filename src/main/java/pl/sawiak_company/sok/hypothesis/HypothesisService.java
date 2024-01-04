package pl.sawiak_company.sok.hypothesis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.hypothesis.dto.HypothesisRequest;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;
import pl.sawiak_company.sok.sociological_project.SociologicalProjectService;

import java.util.List;

@Service
public class HypothesisService {
    @Autowired
    private HypothesisRepository hypothesisRepository;
    @Autowired
    private SociologicalProjectService projectService;

    public Hypothesis createHypothesis(Integer projectId, HypothesisRequest request) {
        SociologicalProject project = projectService.getById(projectId);

        Hypothesis hypothesis = Hypothesis.builder()
                .text(request.getText())
                .sociologicalProject(project)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        hypothesisRepository.save(hypothesis);
        return hypothesis;
    }

    public Hypothesis getById(Integer id) {
        return hypothesisRepository.findById(id)
                .orElseThrow(() -> new RequestException("No HYPOTHESIS within given Id: " + id, HttpStatus.BAD_REQUEST, "HYPOTHESIS_NOT_EXISTS"));
    }

    public List<Hypothesis> getAll() {
        return hypothesisRepository.findAll();
    }

    public Hypothesis editHypothesis(Integer id, HypothesisRequest request) {
        Hypothesis hypothesis = getById(id);

        hypothesis.setText(request.getText());
        hypothesis.setEditionSignature(new EditionSignature());
        hypothesisRepository.save(hypothesis);
        return hypothesis;
    }

    public void deleteHypothesis(Integer id) {
        Hypothesis hypothesis = getById(id);
        hypothesisRepository.delete(hypothesis);
    }
}
