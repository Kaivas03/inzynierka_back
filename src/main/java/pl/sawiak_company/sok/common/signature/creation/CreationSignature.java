package pl.sawiak_company.sok.common.signature.creation;

import lombok.*;
import jakarta.persistence.*;
import pl.sawiak_company.sok.common.signature.Signature;

import java.time.LocalDateTime;
@Data
@Embeddable
public class CreationSignature implements Signature {
    @Column(name = "utworzenie_timestamp")
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;
    public CreationSignature(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public CreationSignature() {
        this(LocalDateTime.now());
    }
    @Override
    public LocalDateTime getDateTime() {
        return createdAt;
    }
}
