package br.com.vr.card.transaction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbstractEntity {

    private String password;

    private String number;

    @Column(name="balance",
            columnDefinition="decimal",
            precision=15,
            scale=2)
    private BigDecimal balance;

    @OneToMany(
            mappedBy = "card",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transaction> transactions;

}
