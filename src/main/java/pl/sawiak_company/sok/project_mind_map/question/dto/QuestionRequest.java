package pl.sawiak_company.sok.project_mind_map.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String text;
    private BigDecimal posX;
    private BigDecimal posY;
}
