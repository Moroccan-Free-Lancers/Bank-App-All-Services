package bank.app.card_service.repositories;

import bank.app.card_service.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findTransactionByTransactionId(String transactionId);
}
