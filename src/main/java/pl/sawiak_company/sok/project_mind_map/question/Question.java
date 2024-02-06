package pl.sawiak_company.sok.project_mind_map.question;

import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.code_group.CodeGroup;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.hypothesis.Hypothesis;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pytanie")
public class Question {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hipoteza")
    private Hypothesis hypothesis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pytanie")
    private Question question;

    @ManyToMany
    @JoinTable(name = "pytanie_kod",
            joinColumns = @JoinColumn(name = "id_pytanie"),
            inverseJoinColumns = @JoinColumn(name = "id_kod")
    )
    @ToString.Exclude
    private List<Code> codes;

    @ManyToMany
    @JoinTable(name = "pytanie_grupa_kodow",
            joinColumns = @JoinColumn(name = "id_pytanie"),
            inverseJoinColumns = @JoinColumn(name = "id_grupa_kodow")
    )
    @ToString.Exclude
    private List<CodeGroup> codeGroups;

    @Column(name = "pozycja_x")
    private BigDecimal posX;
    @Column(name = "pozycja_y")
    private BigDecimal posY;
    @Column(name = "tekst")
    private String text;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Question> questions;
}
