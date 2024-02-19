package pl.sawiak_company.sok.interviews_manager.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interviews_manager.interview.Interview;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {
    private Integer id;
    private String name;
    private String text;
    private Integer quotationAmount;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public InterviewDto(Interview interview) {
        this.id = interview.getId();
        this.name = interview.getName();
        this.text = interview.getText();
        this.quotationAmount = interview.getQuotations() == null ? 0 : interview.getQuotations().size();
        this.creationSignature = interview.getCreationSignature();
        this.editionSignature = interview.getEditionSignature();
    }
}


