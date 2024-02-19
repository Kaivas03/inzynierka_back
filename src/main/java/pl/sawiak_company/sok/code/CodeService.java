package pl.sawiak_company.sok.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.code.dto.CodeRequest;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.code_group.CodeGroupService;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;
import pl.sawiak_company.sok.sociological_project.SociologicalProjectService;

import java.util.List;

@Service
public class CodeService {
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private SociologicalProjectService projectService;
    @Autowired
    private CodeGroupService codeGroupService;

    public Code createCode(Integer projectId, CodeRequest request) {
        SociologicalProject project = projectService.getById(projectId);

        Code code = Code.builder()
                .sociologicalProject(project)
                .name(request.getName())
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        if (request.getCodeGroupId() != null) {
            CodeGroup codeGroup = codeGroupService.getById(request.getCodeGroupId());
            code.setCodeGroup(codeGroup);
        }

        codeRepository.save(code);
        return code;
    }

    public Code getById(Integer id) {
        return codeRepository.findById(id)
                .orElseThrow(() -> new RequestException("No CODE within given Id: " + id, HttpStatus.BAD_REQUEST, "CODE_NOT_EXISTS"));
    }

    public List<Code> getAllByProject(Integer projectId) {
        SociologicalProject project = projectService.getById(projectId);
        return getAllByProject(project);
    }

    public List<Code> getAllByProject(SociologicalProject project) {
        return codeRepository.findAllBySociologicalProject(project);
    }

    public List<Code> getCodesByIds(List<Integer> codeIds) {
        return codeRepository.findAllByIdIn(codeIds);
    }

    public Code editCode(Integer codeId, CodeRequest request) {
        Code code = getById(codeId);

        if (request.getCodeGroupId() != null) {
            CodeGroup codeGroup = codeGroupService.getById(request.getCodeGroupId());
            code.setCodeGroup(codeGroup);
        } else {
            code.setCodeGroup(null);
        }

        code.setName(request.getName());
        code.setEditionSignature(new EditionSignature());
        codeRepository.save(code);
        return code;
    }

    public Code addCodeGroup(Integer codeId, Integer codeGroupId) {
        Code code = getById(codeId);
        CodeGroup codeGroup = codeGroupService.getById(codeGroupId);

        return addCodeGroup(code, codeGroup);
    }

    public Code addCodeGroup(Code code, CodeGroup codeGroup) {
        code.setCodeGroup(codeGroup);
        code.setEditionSignature(new EditionSignature());
        codeRepository.save(code);
        return code;
    }

    public void deleteCode(Integer id) {
        Code code = getById(id);
        deleteCode(code);
    }

    public void deleteCode(Code code) {
        codeRepository.delete(code);
    }
}
