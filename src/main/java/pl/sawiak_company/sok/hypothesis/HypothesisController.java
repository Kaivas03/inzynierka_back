package pl.sawiak_company.sok.hypothesis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.code_group.dto.CodeGroupRequest;
import pl.sawiak_company.sok.hypothesis.dto.HypothesisRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/hypothesis")
public class HypothesisController {
    @Autowired
    private HypothesisService hypothesisService;

    @PostMapping("/{projectId}")
    public ResponseEntity<Hypothesis> create(@PathVariable(name = "projectId") Integer projectId,
                                             @RequestBody HypothesisRequest request) {
        Hypothesis hypothesis = hypothesisService.createHypothesis(projectId, request);
        log.info("Created new Hypothesis {}", hypothesis);
        return new ResponseEntity<>(hypothesis, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Hypothesis> edit(@PathVariable(name = "id") Integer id,
                                           @RequestBody HypothesisRequest request) {
        Hypothesis hypothesis = hypothesisService.editHypothesis(id, request);
        log.info("Updated Hypothesis {}", id);
        return new ResponseEntity<>(hypothesis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hypothesis> getById(@PathVariable(name = "id") Integer id) {
        Hypothesis hypothesis = hypothesisService.getById(id);
        return new ResponseEntity<>(hypothesis, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hypothesis>> getAll() {
        List<Hypothesis> hypotheses = hypothesisService.getAll();
        return new ResponseEntity<>(hypotheses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        hypothesisService.deleteHypothesis(id);
        log.info("Deleted Hypothesis {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
