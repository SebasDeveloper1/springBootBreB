package co.com.semillero.springBootBreB.dto;

import java.time.LocalDateTime;

/**
 * DTO para representar la respuesta de una transacción de transferencia.
 * Contiene información detallada sobre el estado de la transacción.
 * <p>
 * Atributos:
 * - transactionId: Identificador único de la transacción.
 * - sourceAccountId: Identificador de la cuenta de origen.
 * - destinationAccountId: Identificador de la cuenta de destino.
 * - amount: Monto de la transacción.
 * - date: Fecha y hora en que se realizó la transacción.
 * - status: Estado de la transacción, que puede ser "COMPLETED" o "FAILED".
 * - message: Mensaje descriptivo del estado de la transacción, incluyendo
 *   la razón del fallo si aplica.
 */
public class TransactionResponseDTO {

    private Long transactionId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private Double amount;
    private LocalDateTime date;
    private String status;
    private String message;

    // Constructor vacío
    public TransactionResponseDTO() {}

    // Constructor completo
    public TransactionResponseDTO(Long transactionId, Long sourceAccountId, Long destinationAccountId,
                                  Double amount, LocalDateTime date, String status, String message) {
        this.transactionId = transactionId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.message = message;
    }

    // Getters y Setters

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
