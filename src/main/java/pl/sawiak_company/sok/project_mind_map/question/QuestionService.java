package pl.sawiak_company.sok.project_mind_map.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;
import pl.sawiak_company.sok.project_mind_map.hypothesis.HypothesisService;
import pl.sawiak_company.sok.project_mind_map.question.dto.QuestionRequest;

import java.math.BigDecimal;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private HypothesisService hypothesisService;

    public Question createQuestion(Integer questionId, QuestionRequest request) {
        Question parentQuestion = getById(questionId);

        Question question = Question.builder()
                .text(request.getText())
                .question(parentQuestion)
                .hypothesis(parentQuestion.getHypothesis())
                .posX(request.getPosX())
                .posY(request.getPosY())
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        questionRepository.save(question);
        return question;
    }

    public Question createHypothesisQuestion(Hypothesis hypothesis, QuestionRequest request) {
        Question question = Question.builder()
                .text(request.getText())
                .hypothesis(hypothesis)
                .posX(request.getPosX())
                .posY(request.getPosY())
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        questionRepository.save(question);
        return question;
    }

    public Question getById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RequestException("No QUESTION within given Id: " + id, HttpStatus.BAD_REQUEST, "QUESTION_NOT_EXISTS"));
    }

    public List<Question> getAllByHypothesis(Integer hypothesisId) {
        Hypothesis hypothesis = hypothesisService.getById(hypothesisId);
        return getAllByHypothesis(hypothesis);
    }

    public List<Question> getAllByHypothesis(Hypothesis hypothesis) {
        return questionRepository.findAllByHypothesis(hypothesis);
    }

    public Question editQuestion(Integer id, QuestionRequest request) {
        Question question = getById(id);

        question.setText(request.getText());
        question.setPosX(request.getPosX());
        question.setPosY(request.getPosY());
        question.setEditionSignature(new EditionSignature());
        questionRepository.save(question);
        return question;
    }

    public void editQuestionPosition(Integer id, BigDecimal x, BigDecimal y) {
        Question question = getById(id);

        question.setPosX(x);
        question.setPosY(y);
        question.setEditionSignature(new EditionSignature());
        questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        Question question = getById(id);
        deleteQuestion(question);
    }

    public void deleteQuestion(Question question) {
        List<Question> questions = question.getQuestions();
        questions.forEach(this::deleteQuestion);

        if (question.getQuestion() == null) {
            hypothesisService.deleteOnlyHypothesis(question.getHypothesis());
        }
        questionRepository.delete(question);
    }
}
