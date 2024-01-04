package pl.sawiak_company.sok.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.code.dto.CodeRequest;
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

    public Code createCode(Integer projectId, CodeRequest request) {
        SociologicalProject project = projectService.getById(projectId);

        Code code = Code.builder()
                .sociologicalProject(project)
                .name(request.getName())
//                .codeGroup()
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        codeRepository.save(code);
        return code;
    }

    public Code getById(Integer id) {
        return codeRepository.findById(id)
                .orElseThrow(() -> new RequestException("No CODE within given Id: " + id, HttpStatus.BAD_REQUEST, "CODE_NOT_EXISTS"));
    }

    public List<Code> getAll() {
        return codeRepository.findAll();
    }

    public Code editCode(Integer codeId, CodeRequest request) {
        Code code = getById(codeId);

        code.setName(request.getName());
//        code.setCodeGroup();
        code.setEditionSignature(new EditionSignature());
        codeRepository.save(code);
        return code;
    }

    public Code addCodeGroup(Integer codeId, Integer codeGroupId) {
        Code code = getById(codeId);

//        code.setCodeGroup();
        code.setEditionSignature(new EditionSignature());
        codeRepository.save(code);
        return code;
    }

    public void deleteCode(Integer id) {
        Code code = getById(id);
        codeRepository.delete(code);
    }
}
