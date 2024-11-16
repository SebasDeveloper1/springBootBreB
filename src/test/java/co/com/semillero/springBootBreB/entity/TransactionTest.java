package co.com.semillero.springBootBreB.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void test() {
        //Se realiza la isntancia de Transaction
        Transaction transaction = new Transaction();

        // Establecer valores
        transaction.setTransactionId(1L);
        transaction.setSourceAccountId(1L);
        transaction.setDestinationAccountId(1l);
        transaction.setAmount(20000.0);
        LocalDateTime now = LocalDateTime.now();
        transaction.setDate(now);
        transaction.setStatus("Completado");
        transaction.setMessage("Transacción completada exitosamente");

        // Verificar que los valores estén creados correctamente
        assertEquals(1L, transaction.getTransactionId());
        assertEquals(1L, transaction.getSourceAccountId());
        assertEquals(1L, transaction.getDestinationAccountId());
        assertEquals(20000.0, transaction.getAmount());
        assertEquals(now, transaction.getDate());
        assertEquals("Completado", transaction.getStatus());
        assertEquals("Transacción completada exitosamente", transaction.getMessage());
    }

    @Test
    void testClientModificationDates() {
        // Se realizza una instancia de Transaction
        Transaction transaction = new Transaction();

        // Establecer y verificar fechas
        LocalDateTime creationTime = LocalDateTime.now();
        transaction.setDate(creationTime);

        assertEquals(creationTime, transaction.getDate());
    }

    @Test
    void testNullValues() {
        // // Se realizza una instancia de Account sin establecer valores
        Transaction transaction = new Transaction();

        // Verificar que los valores iniciales sean nulos
        assertNull(transaction.getTransactionId());
        assertNull(transaction.getSourceAccountId());
        assertNull(transaction.getDestinationAccountId());
        assertNull(transaction.getAmount());
        assertNull(transaction.getDate());
        assertNull(transaction.getStatus());
        assertNull(transaction.getMessage());
    }
}