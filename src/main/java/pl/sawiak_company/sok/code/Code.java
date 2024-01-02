package pl.sawiak_company.sok.code;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.quotation.Quotation;

import java.util.List;

@Table(name = "kod")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa", nullable = false)
    private String name;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;

    @OneToMany(mappedBy = "code", fetch = FetchType.LAZY)
    private List<Quotation> quotations;
}
