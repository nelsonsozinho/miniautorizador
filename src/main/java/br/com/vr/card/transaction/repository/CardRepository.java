package br.com.vr.card.transaction.repository;

import br.com.vr.card.transaction.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByNumber(String number);

}
