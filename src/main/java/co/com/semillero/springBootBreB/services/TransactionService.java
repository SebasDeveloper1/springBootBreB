package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.dto.TransactionResponseDTO;
import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.repository.AccountRepository;
import co.com.semillero.springBootBreB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las operaciones relacionadas con las transacciones.
 * <p>
 * Anotaciones:
 * - @Service: Indica que esta clase es un servicio de Spring que contiene la lógica de negocio relacionada
 * con las transacciones.
 * - @Autowired: Inyecta automáticamente una instancia del repositorio de transacciones para interactuar con la base de datos.
 */
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionResponseDTO createTransaction(Transaction transaction) {
        // Validación de las cuentas y ejecución de la lógica de transferencia
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
     *
     * @param clientId El ID del cliente.
     * @return Una lista de DTOs de transacciones correspondientes a las cuentas asociadas con el cliente.
     */
    public List<TransactionResponseDTO> getTransactionsByClientId(Long clientId) {
        // Obtener las transacciones donde el cliente es la cuenta de origen o destino
        List<Transaction> transactions = transactionRepository.findBySourceAccountIdOrDestinationAccountId(clientId, clientId);

        // Convertir las transacciones a DTOs
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
}
