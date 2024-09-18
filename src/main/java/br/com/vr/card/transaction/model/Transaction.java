package br.com.vr.card.transaction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends AbstractEntity {

    public Transaction(final Card card, final BigDecimal value, final LocalDateTime time) {
        this.card = card;
        this.value = value;
        this.setLastModifiedAt(time);
    }

    @Column(name="value",
            columnDefinition="decimal",
            precision=15, scale=2)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card")
    private Card card;

}
