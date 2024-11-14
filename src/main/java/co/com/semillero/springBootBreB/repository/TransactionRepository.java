package co.com.semillero.springBootBreB.repository;

import co.com.semillero.springBootBreB.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Transaction.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Busca transacciones por ID de cuenta de origen o destino.
     *
     * @param sourceAccountId ID de la cuenta de origen.
     * @param destinationAccountId ID de la cuenta de destino.
     * @return Lista de transacciones.
     */
    List<Transaction> findBySourceAccountIdOrDestinationAccountId(Long sourceAccountId, Long destinationAccountId);
}
