package pl.sawiak_company.sok.code;

import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project.Project;
import pl.sawiak_company.sok.quotation.Quotation;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kod")
public class Code {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private CodeGroup codeGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "code", fetch = FetchType.LAZY)
    private List<Quotation> quotations;
}
