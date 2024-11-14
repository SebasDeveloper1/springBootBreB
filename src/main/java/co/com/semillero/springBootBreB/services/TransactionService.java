package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.dto.TransactionResponseDTO;
import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.repository.AccountRepository;
import co.com.semillero.springBootBreB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las operaciones relacionadas con las transacciones.
 */
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Crea una nueva transacción (transferencia) en la base de datos.
     */
    public TransactionResponseDTO createTransaction(Transaction transaction) {
        var sourceAccount = accountRepository.findById(transaction.getSourceAccountId());
        var destinationAccount = accountRepository.findById(transaction.getDestinationAccountId());

        if (sourceAccount.isEmpty() || destinationAccount.isEmpty()) {
            return new TransactionResponseDTO(null, transaction.getSourceAccountId(), transaction.getDestinationAccountId(),
                    transaction.getAmount(), LocalDateTime.now(), "FALLIDO", "Una o ambas cuentas no existen");
        }

        if (sourceAccount.get().getBalance() < transaction.getAmount()) {
            return new TransactionResponseDTO(null, transaction.getSourceAccountId(), transaction.getDestinationAccountId(),
                    transaction.getAmount(), LocalDateTime.now(), "FALLIDO", "Saldo insuficiente en la cuenta de origen");
        }

        // Lógica de transferencia exitosa
        sourceAccount.get().setBalance(sourceAccount.get().getBalance() - transaction.getAmount());
        destinationAccount.get().setBalance(destinationAccount.get().getBalance() + transaction.getAmount());
        transaction.setStatus("COMPLETADO");
        transaction.setMessage("Transacción completada exitosamente");

        // Guardar transacción y cuentas en la base de datos
        accountRepository.save(sourceAccount.get());
        accountRepository.save(destinationAccount.get());
        transactionRepository.save(transaction);

        return new TransactionResponseDTO(transaction.getTransactionId(), transaction.getSourceAccountId(),
                transaction.getDestinationAccountId(), transaction.getAmount(), transaction.getDate(), transaction.getStatus(),
                transaction.getMessage());
    }

    /**
     * Obtiene el listado de transacciones correspondientes a un cliente por su ID.
     */
    public List<TransactionResponseDTO> getTransactionsByClientId(Long clientId) {
        List<Transaction> transactions = transactionRepository.findBySourceAccountIdOrDestinationAccountId(clientId, clientId);

        return transactions.stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getTransactionId(),
                        transaction.getSourceAccountId(),
                        transaction.getDestinationAccountId(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getStatus(),
                        transaction.getMessage()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los detalles de una transacción por su ID.
     *
     * @param transactionId El ID de la transacción.
     * @return Los detalles de la transacción en formato DTO.
     */
    public TransactionResponseDTO getTransactionDetails(Long transactionId) {
        // Buscar la transacción por su ID
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isEmpty()) {
            // Si la transacción no existe, puede devolver un DTO con datos vacíos o un mensaje de error
            return new TransactionResponseDTO(null, null, null, null, null, "FALLIDO", "Transacción no encontrada");
        }

        // Si la transacción existe, devolverla en el formato DTO
        Transaction t = transaction.get();
        return new TransactionResponseDTO(
                t.getTransactionId(),
                t.getSourceAccountId(),
                t.getDestinationAccountId(),
                t.getAmount(),
                t.getDate(),
                t.getStatus(),
                t.getMessage()
        );
    }

    /**
     * Elimina una transaccion de la base de datos por su ID.
     *
     * @param transactionId ID de la transaccion a eliminar.
     */
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
