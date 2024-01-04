package pl.sawiak_company.sok.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.code.dto.CodeRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/code")
public class CodeController {
    @Autowired
    private CodeService codeService;

    @PostMapping("/{projectId}")
    public ResponseEntity<Code> create(@PathVariable(name = "projectId") Integer projectId,
                                       @RequestBody CodeRequest request) {
        Code quotation = codeService.createCode(projectId, request);
        log.info("Created new Code {}", quotation);
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Code> edit(@PathVariable(name = "id") Integer id,
                                     @RequestBody CodeRequest request) {
        Code quotation = codeService.editCode(id, request);
        log.info("Updated Code {}", id);
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @PostMapping("/add-code")
    public ResponseEntity<Code> addCode(@RequestParam Integer codeId, Integer codeGroupId) {
        Code code = codeService.addCodeGroup(codeId, codeGroupId);
        log.info("Updated Code {}", codeId);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Code> getById(@PathVariable(name = "id") Integer id) {
        Code code = codeService.getById(id);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Code>> getAll() {
        List<Code> codes = codeService.getAll();
        return new ResponseEntity<>(codes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        codeService.deleteCode(id);
        log.info("Deleted Code {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
