package pl.sawiak_company.sok.project_mind_map.mind_map_dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePositionDto {
    private List<NodeDto> nodes;
}
