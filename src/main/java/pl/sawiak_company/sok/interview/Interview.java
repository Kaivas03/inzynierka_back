package pl.sawiak_company.sok.interview;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.quotation.Quotation;

import java.util.List;

@Table(name = "wywiad")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "interview", fetch = FetchType.LAZY)
    private List<Quotation> quotations;
}
