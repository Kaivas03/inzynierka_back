package pl.sawiak_company.sok.common.signature.edition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.sawiak_company.sok.common.signature.Signature;

import java.time.LocalDateTime;

@Data
@Embeddable
public class EditionSignature implements Signature {
    @Column(name = "edycja_timestamp")
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    public EditionSignature(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EditionSignature() {
        this(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getDateTime() {
        return createdAt;
    }
}
