package pl.sawiak_company.sok.code_group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.code_group.dto.CodeGroupRequest;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;
import pl.sawiak_company.sok.sociological_project.SociologicalProjectService;

import java.util.List;

@Service
public class CodeGroupService {
    @Autowired
    private CodeGroupRepository codeGroupRepository;
    @Autowired
    private SociologicalProjectService projectService;

    public CodeGroup createCodeGroup(Integer projectId, CodeGroupRequest request) {
        SociologicalProject project = projectService.getById(projectId);

        CodeGroup codeGroup = CodeGroup.builder()
                .name(request.getName())
                .sociologicalProject(project)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        codeGroupRepository.save(codeGroup);
        return codeGroup;
    }

    public CodeGroup getById(Integer id) {
        return codeGroupRepository.findById(id)
                .orElseThrow(() -> new RequestException("No CODE_GROUP within given Id: " + id, HttpStatus.BAD_REQUEST, "CODE_GROUP_NOT_EXISTS"));
    }

    public List<CodeGroup> getAllByProject(Integer projectId) {
        SociologicalProject project = projectService.getById(projectId);
        return codeGroupRepository.findAllBySociologicalProject(project);
    }

    public List<CodeGroup> getAllByProject(SociologicalProject project) {
        return codeGroupRepository.findAllBySociologicalProject(project);
    }

    public List<CodeGroup> getAllByIds(List<Integer> codeGroupIds) {
        return codeGroupRepository.findAllByIdIn(codeGroupIds);
    }

    public CodeGroup editCodeGroup(Integer id, CodeGroupRequest request) {
        CodeGroup codeGroup = getById(id);

        codeGroup.setName(request.getName());
        codeGroup.setEditionSignature(new EditionSignature());
        codeGroupRepository.save(codeGroup);
        return codeGroup;
    }

    public void deleteCodeGroup(Integer id) {
        CodeGroup codeGroup = getById(id);
        deleteCodeGroup(codeGroup);
    }

    public void deleteCodeGroup(CodeGroup codeGroup) {
        codeGroupRepository.delete(codeGroup);
    }
}
