package pl.sawiak_company.sok.interview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.code_group.dto.CodeGroupRequest;
import pl.sawiak_company.sok.interview.dto.InterviewRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/interview")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping("/{projectId}")
    public ResponseEntity<Interview> create(@PathVariable(name = "projectId") Integer projectId,
                                            @RequestBody InterviewRequest request) {
        Interview interview = interviewService.createInterview(projectId, request);
        log.info("Created new Interview {}", interview);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Interview> edit(@PathVariable(name = "id") Integer id,
                                          @RequestBody InterviewRequest request) {
        Interview interview = interviewService.editInterview(id, request);
        log.info("Updated Interview {}", id);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interview> getById(@PathVariable(name = "id") Integer id) {
        Interview interview = interviewService.getById(id);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Interview>> getAll() {
        List<Interview> interviews = interviewService.getAll();
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        interviewService.deleteInterview(id);
        log.info("Deleted Interview {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
