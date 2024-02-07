package pl.sawiak_company.sok.project_mind_map.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.code.dto.CodeDto;
import pl.sawiak_company.sok.code_group.dto.CodeGroupDto;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.question.Question;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Integer id;
    private Integer hypothesisId;
    private Integer questionId;
    private List<CodeDto> codes;
    private List<CodeGroupDto> codeGroups;
    private BigDecimal posX;
    private BigDecimal posY;
    private String text;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.hypothesisId = question.getHypothesis().getId();
        this.questionId = question.getQuestion() == null ? null : question.getQuestion().getId();
        this.codes = question.getCodes().stream().map(CodeDto::new).toList();
        this.codeGroups = question.getCodeGroups().stream().map(CodeGroupDto::new).toList();
        this.posX = question.getPosX();
        this.posY = question.getPosY();
        this.text = question.getText();
        this.creationSignature = question.getCreationSignature();
        this.editionSignature = question.getEditionSignature();
    }
}
