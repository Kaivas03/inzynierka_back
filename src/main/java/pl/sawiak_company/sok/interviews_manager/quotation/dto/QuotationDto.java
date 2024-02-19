package pl.sawiak_company.sok.interviews_manager.quotation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interviews_manager.quotation.Quotation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationDto {
    private Integer id;
    private Integer lineNumber;
    private String text;
    private Integer codeId;
    private String codeName;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public QuotationDto(Quotation quotation) {
        this.id = quotation.getId();
        this.lineNumber = quotation.getLineNumber();
        this.text = quotation.getText();
        this.codeId = quotation.getCode() == null ? null : quotation.getCode().getId();
        this.codeName = quotation.getCode() == null ? null : quotation.getCode().getName();
        this.creationSignature = quotation.getCreationSignature();
        this.editionSignature = quotation.getEditionSignature();
    }
}
