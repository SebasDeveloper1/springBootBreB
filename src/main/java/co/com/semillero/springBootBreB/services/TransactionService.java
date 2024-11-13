package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.entity.Transaction;
import co.com.semillero.springBootBreB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TransactionRepository transactionRepository;

    /**
     * Crea una nueva transacción en la base de datos.
     * <p>
     * En esta función se pueden agregar validaciones adicionales, como verificar que el cliente tenga fondos suficientes,
     * la validez de las cuentas involucradas, el estado de la transacción, entre otras.
     *
     * @param transaction Objeto Transacción a crear, recibido como parámetro.
     * @return La transacción creada y guardada en la base de datos.
     */
    public Transaction createTransaction(Transaction transaction) {
        // Aquí puedes validar fondos, estados, etc.
        return transactionRepository.save(transaction);
    }
}
