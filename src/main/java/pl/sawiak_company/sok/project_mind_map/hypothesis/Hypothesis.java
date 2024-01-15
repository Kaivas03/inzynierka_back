package pl.sawiak_company.sok.project_mind_map.hypothesis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.project_mind_map.hypothesis.dto.HypothesisSerializer;
import pl.sawiak_company.sok.project_mind_map.question.Question;
import pl.sawiak_company.sok.sociological_project.SociologicalProject;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hipoteza")
@JsonSerialize(using = HypothesisSerializer.class)
public class Hypothesis {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tekst", nullable = false)
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_badanie")
    private SociologicalProject sociologicalProject;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pytanie")
    private Question question;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;
}
