package pl.sawiak_company.sok.project_mind_map.mind_map_dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {
    private String id;
    private DataFlow data;
    private String type;
    private Position position;
}
