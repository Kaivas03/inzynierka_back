package pl.sawiak_company.sok.project;

import jakarta.persistence.*;
import lombok.*;
import pl.sawiak_company.sok.common.signature.creation.CreationSignature;
import pl.sawiak_company.sok.common.signature.edition.EditionSignature;

@Table(name = "badanie")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
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
}
