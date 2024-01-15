package pl.sawiak_company.sok.interviews_manager.quotation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationRequest {
    private String text;
    private Integer lineNumber;
    private Integer codeId;
}
