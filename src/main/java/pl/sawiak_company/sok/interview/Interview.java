package pl.sawiak_company.sok.interview;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "wywiad")
public class Interview {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tekst", nullable = false, length = 2500)
    private String text;
    @Column(name = "nazwa")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "interview", fetch = FetchType.LAZY)
    private List<Quotation> quotations;
}
