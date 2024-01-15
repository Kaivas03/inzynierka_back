package pl.sawiak_company.sok.project_mind_map.mind_map_dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private BigDecimal x;
    private BigDecimal y;
}
