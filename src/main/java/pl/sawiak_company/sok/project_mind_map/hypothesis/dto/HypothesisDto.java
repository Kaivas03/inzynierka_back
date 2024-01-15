package pl.sawiak_company.sok.project_mind_map.hypothesis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HypothesisDto {
    private Integer id;
    private String text;
    private Integer questionId;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public HypothesisDto(Hypothesis hypothesis) {
        this.id = hypothesis.getId();
        this.text = hypothesis.getText();
        this.questionId = hypothesis.getQuestion().getId();
        this.creationSignature = hypothesis.getCreationSignature();
        this.editionSignature = hypothesis.getEditionSignature();
    }
}
