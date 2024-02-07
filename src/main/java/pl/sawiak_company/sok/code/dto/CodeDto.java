package pl.sawiak_company.sok.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto {
    private Integer id;
    private String name;
    private Integer codeGroupId;
    private Integer projectId;
    private Integer quotationAmount;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public CodeDto(Code code) {
        this.id = code.getId();
        this.name = code.getName();
        this.codeGroupId = code.getCodeGroup() == null ? null : code.getCodeGroup().getId();
        this.projectId = code.getSociologicalProject().getId();
        this.quotationAmount = code.getQuotations().size();
        this.creationSignature = code.getCreationSignature();
        this.editionSignature = code.getEditionSignature();
    }
}
