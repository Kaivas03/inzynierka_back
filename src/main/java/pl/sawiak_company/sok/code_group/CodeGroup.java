package pl.sawiak_company.sok.code_group;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code_group.dto.CodeGroupDto;
import pl.sawiak_company.sok.code_group.dto.CodeGroupSerializer;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grupa_kodow")
@JsonSerialize(using = CodeGroupSerializer.class)
public class CodeGroup {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_badanie")
    private SociologicalProject sociologicalProject;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "codeGroup", fetch = FetchType.LAZY)
    private List<Code> codes;
}
