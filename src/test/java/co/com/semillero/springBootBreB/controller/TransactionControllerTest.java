package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.dto.TransactionResponseDTO;
import co.com.semillero.springBootBreB.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Prueba unitaria para verificar que el método "getTransactionsByClientId" en el controlador
     * retorna correctamente las transacciones asociadas a un cliente.
     */
    @Test
    public void testGetTransactionsByClientId() throws Exception {
        // Datos simulados
        TransactionResponseDTO transaction1 = new TransactionResponseDTO(1L, 1L, 2L, 100.0, LocalDateTime.now(), "COMPLETADO", "Exitosa");
        TransactionResponseDTO transaction2 = new TransactionResponseDTO(2L, 1L, 3L, 200.0, LocalDateTime.now(), "COMPLETADO", "Exitosa");

        // Mock del servicio
        Mockito.when(transactionService.getTransactionsByClientId(eq(1L))).thenReturn(List.of(transaction1, transaction2));

        // Simulación de la solicitud GET
        mockMvc.perform(get("/api/transfers/client-transactions")
                        .header("Client-Id", 1L))
                .andExpect(status().isOk()) // Verificar que la respuesta sea HTTP 200
                .andExpect(jsonPath("$.size()").value(2)) // Verificar que hay dos transacciones en la respuesta
                .andExpect(jsonPath("$[0].transactionId").value(1L)) // Verificar el ID de la primera transacción
                .andExpect(jsonPath("$[1].transactionId").value(2L)); // Verificar el ID de la segunda transacción
    }

    /**
     * Prueba unitaria para verificar que el método "getTransactionDetails" en el controlador
     * retorna los detalles de una transacción específica.
     */
    @Test
    public void testGetTransactionDetails() throws Exception {
        // Datos simulados
        TransactionResponseDTO responseDTO = new TransactionResponseDTO(1L, 1L, 2L, 100.0, LocalDateTime.now(), "COMPLETADO", "Exitosa");

        // Mock del servicio
        Mockito.when(transactionService.getTransactionDetails(eq(1L))).thenReturn(responseDTO);

        // Simulación de la solicitud GET
        mockMvc.perform(get("/api/transfers/1"))
                .andExpect(status().isOk()) // Verificar que la respuesta sea HTTP 200
                .andExpect(jsonPath("$.transactionId").value(1L)) // Verificar el ID de la transacción
                .andExpect(jsonPath("$.status").value("COMPLETADO")); // Verificar el estado de la transacción
    }

    /**
     * Prueba unitaria para verificar que el método "deleteTransaction" en el controlador
     * elimina correctamente una transacción por su ID.
     */
    @Test
    public void testDeleteTransaction() throws Exception {
        // Simulación de la solicitud DELETE
        mockMvc.perform(delete("/api/transfers/1"))
                .andExpect(status().isOk()); // Verificar que la respuesta sea HTTP 200

        // Verificar que el servicio se invoque correctamente
        Mockito.verify(transactionService).deleteTransaction(1L);
    }
}
