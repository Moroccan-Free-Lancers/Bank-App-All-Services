package bank.app.card_service.repositories;

import bank.app.card_service.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository
        extends JpaRepository<Card, String> {

    Optional<Card> findByCardId(String cardId);
    List<Card> findAllByAccountId(String accountId);
}



