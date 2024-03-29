package pl.sawiak_company.sok.project_mind_map.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code.CodeService;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.code_group.CodeGroupService;
import pl.sawiak_company.sok.common.exceptions.RequestException;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;
import pl.sawiak_company.sok.project_mind_map.hypothesis.HypothesisService;
import pl.sawiak_company.sok.project_mind_map.question.dto.QuestionRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private HypothesisService hypothesisService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private CodeGroupService codeGroupService;

    public Question createQuestion(Integer questionId, QuestionRequest request) {
        Question parentQuestion = getById(questionId);
        List<Code> codes = codeService.getCodesByIds(request.getCodeIds());
        List<CodeGroup> codeGroups = codeGroupService.getAllByIds(request.getCodeGroupIds());

        Question question = Question.builder()
                .text(request.getText())
                .question(parentQuestion)
                .hypothesis(parentQuestion.getHypothesis())
                .posX(request.getPosX())
                .posY(request.getPosY())
                .codes(codes)
                .codeGroups(codeGroups)
                .creationSignature(new CreationSignature())
                .editionSignature(new EditionSignature())
                .build();

        questionRepository.save(question);
        return question;
    }

    public Question createHypothesisQuestion(Hypothesis hypothesis) {
        Question question = Question.builder()
                .text(hypothesis.getText())
                .hypothesis(hypothesis)
                .posX(BigDecimal.ZERO)
                .posY(BigDecimal.ZERO)
                .codes(new ArrayList<>())
                .codeGroups(new ArrayList<>())
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
        List<Code> codes = codeService.getCodesByIds(request.getCodeIds());
        List<CodeGroup> codeGroups = codeGroupService.getAllByIds(request.getCodeGroupIds());

        question.setText(request.getText());
        question.setPosX(request.getPosX());
        question.setPosY(request.getPosY());
        question.setCodes(codes);
        question.setCodeGroups(codeGroups);
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

        questionRepository.delete(question);
    }

    public void deleteQuestionOrHypothesis(Integer id) {
        Question question = getById(id);
        deleteQuestionOrHypothesis(question);
    }

    public void deleteQuestionOrHypothesis(Question question) {
        if (question.getQuestion() == null) {
            hypothesisService.deleteHypothesis(question.getHypothesis());
        } else {
            List<Question> questions = question.getQuestions();
            questions.forEach(this::deleteQuestion);

            questionRepository.delete(question);
        }
    }
}
