package pl.sawiak_company.sok.quotation;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interview.Interview;

@Table(name = "cytat")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quotation {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tekst", nullable = false, length = 2500)
    private String text;
    @Column(name = "numer_linijki")
    private Integer lineNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Code code;
    @ManyToOne(fetch = FetchType.LAZY)
    private Interview interview;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;
}
