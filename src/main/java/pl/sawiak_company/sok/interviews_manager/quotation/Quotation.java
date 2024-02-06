package pl.sawiak_company.sok.interviews_manager.quotation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.code.Code;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;
import pl.sawiak_company.sok.interviews_manager.interview.Interview;
import pl.sawiak_company.sok.interviews_manager.quotation.dto.QuotationSerializer;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cytat")
@JsonSerialize(using = QuotationSerializer.class)
public class Quotation {
    @Id
    @Column(name = "id")
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tekst", length = 2500)
    private String text;
    @Column(name = "numer_linijki")
    private Integer lineNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kod")
    private Code code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wywiad")
    private Interview interview;
    @Embedded
    private CreationSignature creationSignature;
    @Embedded
    private EditionSignature editionSignature;
}
