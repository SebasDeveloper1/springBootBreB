package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.dto.TransactionResponseDTO;
import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con las transferencias.
 */
@RestController
@RequestMapping("/api/transfers")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Crea una nueva transacción (transferencia) en la base de datos.
     * Recibe un objeto de transacción en el cuerpo de la solicitud y lo pasa al servicio para su procesamiento.
     *
     * @param transaction Objeto Transacción a crear, recibido en el cuerpo de la solicitud.
     * @return La transacción creada y su estado detallado.
     */
    @PostMapping
    public TransactionResponseDTO createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    /**
     * Obtiene todas las transacciones relacionadas con un cliente por su ID.
     *
     * @param clientId El ID del cliente.
     * @return Una lista de transacciones asociadas al cliente.
     */
    @GetMapping("/client/{clientId}")
    public List<TransactionResponseDTO> getTransactionsByClientId(@PathVariable Long clientId) {
        return transactionService.getTransactionsByClientId(clientId);
    }

    /**
     * Obtiene los detalles de una transacción por su ID.
     *
     * @param transactionId El ID de la transacción.
     * @return Los detalles de la transacción, si existe, o un mensaje de error si no.
     */
    @GetMapping("/{transactionId}")
    public TransactionResponseDTO getTransactionDetails(@PathVariable Long transactionId) {
        return transactionService.getTransactionDetails(transactionId);
    }

    /**
     * Elimina un transaccion de la base de datos por su ID.
     *
     * @param transactionId ID del transaccion a eliminar, obtenido de la ruta.
     */
    @DeleteMapping("/{transactionId}")
    public void deleteUser(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
