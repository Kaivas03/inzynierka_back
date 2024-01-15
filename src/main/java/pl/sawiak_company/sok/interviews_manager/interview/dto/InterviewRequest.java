package pl.sawiak_company.sok.interviews_manager.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRequest {
    private String text;
    private String name;
}
