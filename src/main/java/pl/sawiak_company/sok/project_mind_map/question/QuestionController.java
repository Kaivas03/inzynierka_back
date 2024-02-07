package pl.sawiak_company.sok.project_mind_map.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sawiak_company.sok.project_mind_map.question.dto.QuestionRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/{questionId}")
    public ResponseEntity<Question> createHypothesisQuestion(@PathVariable(name = "questionId") Integer questionId,
                                                             @RequestBody QuestionRequest request) {
        Question question = questionService.createQuestion(questionId, request);
        log.info("Created new Question {}", question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Question> edit(@PathVariable(name = "id") Integer id,
                                         @RequestBody QuestionRequest request) {
        Question question = questionService.editQuestion(id, request);
        log.info("Updated Question {}", id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable(name = "id") Integer id) {
        Question question = questionService.getById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/all/{hypothesisId}")
    public ResponseEntity<List<Question>> getAll(@PathVariable(name = "hypothesisId") Integer hypothesisId) {
        List<Question> questions = questionService.getAllByHypothesis(hypothesisId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        questionService.deleteQuestionOrHypothesis(id);
        log.info("Deleted Question {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
