package co.com.semillero.springBootBreB.dto;

/**
 * DTO (Data Transfer Object) utilizado para la creación de una cuenta.
 * Este objeto encapsula los datos necesarios para registrar una nueva cuenta en el sistema.
 */
public class AccountRequestDTO {

    private Long clientId;     // ID del cliente asociado a la cuenta.
    private String accountKey; // Alias o llave para identificar la cuenta.
    private Double initialBalance; // Saldo inicial de la cuenta.
    private String accountType;   // Tipo de cuenta (ahorro, corriente, etc.).
    private String bank;         // Banco donde se abrirá la cuenta.

    // Constructor
    public AccountRequestDTO() {
    }

    // Constructor con parámetros
    public AccountRequestDTO(Long clientId, String accountKey, Double initialBalance, String accountType, String bank) {
        this.clientId = clientId;
        this.accountKey = accountKey;
        this.initialBalance = initialBalance;
        this.accountType = accountType;
        this.bank = bank;
    }

    // Getters y Setters

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
