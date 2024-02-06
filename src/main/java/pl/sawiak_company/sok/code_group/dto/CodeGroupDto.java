package pl.sawiak_company.sok.code_group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupDto {
    private Integer id;
    private String name;
    private Integer projectId;
    private Integer codeAmount;
    private CreationSignature creationSignature;
    private EditionSignature editionSignature;

    public CodeGroupDto(CodeGroup codeGroup) {
        this.id = codeGroup.getId();
        this.name = codeGroup.getName();
        this.projectId = codeGroup.getSociologicalProject().getId();
        this.codeAmount = codeGroup.getCodes().size();
        this.creationSignature = codeGroup.getCreationSignature();
        this.editionSignature = codeGroup.getEditionSignature();
    }
}
