package pl.sawiak_company.sok.project_mind_map.hypothesis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.project_mind_map.hypothesis.dto.HypothesisRequest;

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

    @GetMapping("/all/{projectId}")
    public ResponseEntity<List<Hypothesis>> getAll(@PathVariable(name = "projectId") Integer projectId) {
        List<Hypothesis> hypotheses = hypothesisService.getAllByProject(projectId);
        return new ResponseEntity<>(hypotheses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        hypothesisService.deleteHypothesis(id);
        log.info("Deleted Hypothesis {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
