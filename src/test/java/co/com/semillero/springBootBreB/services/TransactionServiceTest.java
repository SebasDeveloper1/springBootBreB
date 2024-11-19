package co.com.semillero.springBootBreB.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import co.com.semillero.springBootBreB.dto.TransactionResponseDTO;
import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.repository.AccountRepository;
import co.com.semillero.springBootBreB.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account sourceAccount;
    private Account destinationAccount;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        sourceAccount = new Account();
        sourceAccount.setAccountId(1L);
        sourceAccount.setBalance(10000.0);

        destinationAccount = new Account();
        destinationAccount.setAccountId(2L);
        destinationAccount.setBalance(50000.0);

        transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setSourceAccountId(1L);
        transaction.setDestinationAccountId(2L);
        transaction.setAmount(20000.0);
        transaction.setDate(LocalDateTime.now());
        transaction.setStatus("COMPLETADO");
        transaction.setMessage("Transacción completada exitosamente");

        // Mocking de las respuestas de los repositorios
        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(sourceAccount).thenReturn(destinationAccount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
    }

//    //Crear transacción exitosa
//    @Test
//    public void testCreateTransaction_Success() {
//        // Asegurarse de que las cuentas sean encontradas correctamente
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
//        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));
//
//        // Simulando que las cuentas se guardan correctamente
//        when(accountRepository.save(any(Account.class))).thenReturn(sourceAccount).thenReturn(destinationAccount);
//
//        // Simulando la transacción que se guarda correctamente
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        // Ejecutar el metodo
//        TransactionResponseDTO response = transactionService.createTransaction(transaction);
//
//        // Verificar resultados
//        assertEquals("COMPLETADO", response.getStatus());
//        assertEquals("Transacción completada exitosamente", response.getMessage());
//        verify(accountRepository, times(2)).save(any(Account.class));
//        verify(transactionRepository, times(1)).save(any(Transaction.class));
//    }

    //Cuenta origen no encontrada
    @Test
    public void testCreateTransaction_SourceAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));

        TransactionResponseDTO response = transactionService.createTransaction(transaction);

        assertEquals("FALLIDO", response.getStatus());
        assertEquals("Una o ambas cuentas no existen", response.getMessage());
    }

    //Cuenta destino no encontrada
    @Test
    public void testCreateTransaction_DestinationAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());

        TransactionResponseDTO response = transactionService.createTransaction(transaction);

        assertEquals("FALLIDO", response.getStatus());
        assertEquals("Una o ambas cuentas no existen", response.getMessage());
    }

    // Saldo insuficiente en cuenta de origen
    @Test
    public void testCreateTransaction_InsufficientBalance() {
        sourceAccount.setBalance(50.0); // saldo insuficiente

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));

        TransactionResponseDTO response = transactionService.createTransaction(transaction);

        assertEquals("FALLIDO", response.getStatus());
        assertEquals("Saldo insuficiente en la cuenta de origen", response.getMessage());
    }

    //Obtener transacciones por cliente
    @Test
    public void testGetTransactionsByClientId() {
        Transaction transaction1 = new Transaction();
        transaction1.setSourceAccountId(1L);
        transaction1.setDestinationAccountId(2L);
        transaction1.setAmount(20000.0);

        Transaction transaction2 = new Transaction();
        transaction2.setSourceAccountId(2L);
        transaction2.setDestinationAccountId(1L);
        transaction2.setAmount(10000.0);

        when(transactionRepository.findBySourceAccountIdOrDestinationAccountId(1L, 1L)).thenReturn(List.of(transaction1, transaction2));

        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByClientId(1L);

        assertEquals(2, transactions.size());
    }

    //Obtener detalles de transacción
    @Test
    public void testGetTransactionDetails_Found() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        TransactionResponseDTO response = transactionService.getTransactionDetails(1L);

        assertEquals(transaction.getTransactionId(), response.getTransactionId());
        assertEquals("COMPLETADO", response.getStatus());
    }

    //Obtener detalles de transacción no encontrada
    @Test
    public void testGetTransactionDetails_NotFound() {
        when(transactionRepository.findById(999L)).thenReturn(Optional.empty());

        TransactionResponseDTO response = transactionService.getTransactionDetails(999L);

        assertEquals("FALLIDO", response.getStatus());
        assertEquals("Transacción no encontrada", response.getMessage());
    }

    //Eliminar transacción
    @Test
    public void testDeleteTransaction() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }
}
