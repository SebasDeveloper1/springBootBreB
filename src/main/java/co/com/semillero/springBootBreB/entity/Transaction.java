package co.com.semillero.springBootBreB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Representa una entidad de Transacción en la aplicación, la cual contiene la
 * información de las transacciones realizadas entre cuentas.
 * <p>
 * Anotaciones:
 * - @Entity: Marca esta clase como una entidad de JPA, que será mapeada a
 * una tabla de base de datos.
 * - @Id: Define el atributo que actúa como clave primaria.
 * - @GeneratedValue: Establece la estrategia de generación de la clave
 * primaria, en este caso, Identity.
 * - @CreationTimestamp: Marca el momento de creación de la transacción.
 */
@Entity
public class Transaction {

    /**
     * Identificador único de la transacción, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    /**
     * Identificador de la cuenta de origen de la transacción.
     */
    private Long sourceAccountId;

    /**
     * Identificador de la cuenta de destino de la transacción.
     */
    private Long destinationAccountId;

    /**
     * Monto transferido en la transacción.
     */
    private Double amount;

    /**
     * Fecha en que se realizó la transacción, asignada automáticamente.
     */
    @CreationTimestamp
    private LocalDateTime date;

    /**
     * Estado de la transacción (ej., completada, fallida, pendiente).
     */
    private String status;

    // Getters y setters

    /**
     * Obtiene el ID de la transacción.
     *
     * @return transactionId ID de la transacción.
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el ID de la transacción.
     *
     * @param transactionId ID de la transacción.
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Obtiene el ID de la cuenta de origen.
     *
     * @return sourceAccountId ID de la cuenta de origen.
     */
    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    /**
     * Establece el ID de la cuenta de origen.
     *
     * @param sourceAccountId ID de la cuenta de origen.
     */
    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    /**
     * Obtiene el ID de la cuenta de destino.
     *
     * @return destinationAccountId ID de la cuenta de destino.
     */
    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    /**
     * Establece el ID de la cuenta de destino.
     *
     * @param destinationAccountId ID de la cuenta de destino.
     */
    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    /**
     * Obtiene el monto de la transacción.
     *
     * @return amount Monto de la transacción.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Establece el monto de la transacción.
     *
     * @param amount Monto de la transacción.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return date Fecha de la transacción.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Establece la fecha de la transacción.
     *
     * @param date Fecha de la transacción.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Obtiene el estado de la transacción.
     *
     * @return status Estado de la transacción.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado de la transacción.
     *
     * @param status Estado de la transacción.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
