package pl.sawiak_company.sok.hypothesis;

import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project.Project;
import pl.sawiak_company.sok.question.Question;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hipoteza")
public class Hypothesis {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tekst", nullable = false)
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "hypothesis", fetch = FetchType.LAZY)
    private List<Question> questions;
}
