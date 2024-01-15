package pl.sawiak_company.sok.sociological_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code.CodeService;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.code_group.CodeGroupService;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interviews_manager.interview.Interview;
import pl.sawiak_company.sok.interviews_manager.interview.InterviewService;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;
import pl.sawiak_company.sok.project_mind_map.hypothesis.HypothesisService;
import pl.sawiak_company.sok.sociological_project.dto.SocProjectRequest;

import java.util.List;

@Service
public class SociologicalProjectService {
    @Autowired
    private SociologicalProjectRepository projectRepository;
    @Autowired
    private CodeService codeService;
    @Autowired
    private CodeGroupService codeGroupService;
    @Autowired
    private HypothesisService hypothesisService;
    @Autowired
    private InterviewService interviewService;

    public SociologicalProject createProject(SocProjectRequest request) {
        SociologicalProject project = SociologicalProject.builder()
                .name(request.getName())
                .description(request.getDescription())
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

    public SociologicalProject editProject(Integer id, SocProjectRequest request) {
        SociologicalProject project = getById(id);

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setEditionSignature(new EditionSignature());
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public void deleteProject(Integer id) {
        SociologicalProject project = getById(id);

        List<Interview> interviews = interviewService.getAllByProject(project);
        interviews.forEach(interview -> interviewService.deleteInterview(interview));

        List<Code> codes = codeService.getAllByProject(project);
        codes.forEach(code -> codeService.deleteCode(code));

        List<CodeGroup> codeGroups = codeGroupService.getAllByProject(project);
        codeGroups.forEach(codeGroup -> codeGroupService.deleteCodeGroup(codeGroup));

        List<Hypothesis> hypotheses = hypothesisService.getAllByProject(project);
        hypotheses.forEach(hypothesis -> hypothesisService.deleteHypothesis(hypothesis));

        projectRepository.delete(project);
    }
}
