package pl.sawiak_company.sok.code_group;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.code_group.dto.CodeGroupRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/code-group")
public class CodeGroupController {
    @Autowired
    private CodeGroupService codeGroupService;

    @PostMapping("/{projectId}")
    public ResponseEntity<CodeGroup> create(@PathVariable(name = "projectId") Integer projectId,
                                            @RequestBody CodeGroupRequest request) {
        CodeGroup codeGroup = codeGroupService.createCodeGroup(projectId, request);
        log.info("Created new CodeGroup {}", codeGroup);
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<CodeGroup> edit(@PathVariable(name = "id") Integer id,
                                          @RequestBody CodeGroupRequest request) {
        CodeGroup codeGroup = codeGroupService.editCodeGroup(id, request);
        log.info("Updated CodeGroup {}", id);
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeGroup> getById(@PathVariable(name = "id") Integer id) {
        CodeGroup codeGroup = codeGroupService.getById(id);
        return new ResponseEntity<>(codeGroup, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CodeGroup>> getAllByProject(@PathVariable(name = "projectId") Integer projectId) {
        List<CodeGroup> codeGroups = codeGroupService.getAllByProject(projectId);
        return new ResponseEntity<>(codeGroups, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        codeGroupService.deleteCodeGroup(id);
        log.info("Deleted CodeGroup {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
