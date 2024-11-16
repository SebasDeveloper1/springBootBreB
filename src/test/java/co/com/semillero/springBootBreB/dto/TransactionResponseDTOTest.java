package co.com.semillero.springBootBreB.dto;

import co.com.semillero.springBootBreB.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionResponseDTOTest {

    @Test
    void testTransactionResponseDTO() {
        TransactionResponseDTO  transactionResponseDTO = new TransactionResponseDTO(1L, 1L
                , 1l, 20000.0, LocalDateTime.now(), "Completado"
                , "Transacción completada exitosamente");

        // Establecer valores
        transactionResponseDTO.setTransactionId(1L);
        transactionResponseDTO.setSourceAccountId(1L);
        transactionResponseDTO.setDestinationAccountId(1l);
        transactionResponseDTO.setAmount(20000.0);
        LocalDateTime now = LocalDateTime.now();
        transactionResponseDTO.setDate(now);
        transactionResponseDTO.setStatus("Completado");
        transactionResponseDTO.setMessage("Transacción completada exitosamente");

        // Verificar que los valores estén creados correctamente
        assertEquals(1L,transactionResponseDTO.getTransactionId());
        assertEquals(1L, transactionResponseDTO.getSourceAccountId());
        assertEquals(1L, transactionResponseDTO.getDestinationAccountId());
        assertEquals(20000.0, transactionResponseDTO.getAmount());
        assertEquals(now, transactionResponseDTO.getDate());
        assertEquals("Completado", transactionResponseDTO.getStatus());
        assertEquals("Transacción completada exitosamente", transactionResponseDTO.getMessage());
    }

    @Test
    void testClientModificationDates() {
        // Se realizza una instancia de Transaction
        TransactionResponseDTO  transactionResponseDTO = new TransactionResponseDTO(1L, 1L
                , 1l, 20000.0, LocalDateTime.now(), "Completado"
                , "Transacción completada exitosamente");

        // Establecer y verificar fechas
        LocalDateTime creationTime = LocalDateTime.now();
        transactionResponseDTO.setDate(creationTime);

        assertEquals(creationTime, transactionResponseDTO.getDate());
    }

    @Test
    void testNullValues() {
        // // Se realizza una instancia de Account sin establecer valores
        TransactionResponseDTO  transactionResponseDTO = new TransactionResponseDTO(1L, 1L
                , 1l, 20000.0, LocalDateTime.now(), "Completado"
                , "Transacción completada exitosamente");

        // Verificar que los valores iniciales no sean nulos
        assertNotNull(transactionResponseDTO.getTransactionId());
        assertNotNull(transactionResponseDTO.getSourceAccountId());
        assertNotNull(transactionResponseDTO.getDestinationAccountId());
        assertNotNull(transactionResponseDTO.getAmount());
        assertNotNull(transactionResponseDTO.getDate());
        assertNotNull(transactionResponseDTO.getStatus());
        assertNotNull(transactionResponseDTO.getMessage());
    }

}