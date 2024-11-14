package co.com.semillero.springBootBreB.dto;

import java.time.LocalDateTime;

/**
 * DTO para la respuesta de una transacción.
 */
public class TransactionResponseDTO {

    private Long transactionId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private Double amount;
    private LocalDateTime date;
    private String status;
    private String message;

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

    // Getters y setters
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
