package pl.sawiak_company.sok.code;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.code.dto.CodeSerializer;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;
import pl.sawiak_company.sok.interviews_manager.quotation.Quotation;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kod")
@JsonSerialize(using = CodeSerializer.class)
public class Code {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupa_kodow")
    private CodeGroup codeGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_badanie")
    private SociologicalProject sociologicalProject;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "code", fetch = FetchType.LAZY)
    private List<Quotation> quotations;
}
