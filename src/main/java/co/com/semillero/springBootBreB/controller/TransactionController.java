package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las operaciones relacionadas con las transferencias.
 * <p>
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador de Spring que responde con datos en formato JSON.
 * - @RequestMapping("/api/transfers"): Define el prefijo de la URL para todas las rutas en este controlador.
 * - @Autowired: Inyecta automáticamente el servicio de transacciones para interactuar con la lógica de negocio.
 */
@RestController
@RequestMapping("/api/transfers")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Crea una nueva transacción (transferencia) en la base de datos.
     * <p>
     * Recibe un objeto de transacción en el cuerpo de la solicitud y lo pasa al servicio para su procesamiento.
     *
     * @param transaction Objeto Transacción a crear, recibido en el cuerpo de la solicitud.
     * @return La transacción creada y guardada en la base de datos.
     */
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }
}
