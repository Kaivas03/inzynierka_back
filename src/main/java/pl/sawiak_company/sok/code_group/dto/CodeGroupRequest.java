package pl.sawiak_company.sok.code_group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupRequest {
    private String name;
    private List<Integer> codeIds;
}
