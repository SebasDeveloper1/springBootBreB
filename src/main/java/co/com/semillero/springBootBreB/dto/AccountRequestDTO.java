package co.com.semillero.springBootBreB.dto;

/**
 * DTO para la creación de una cuenta bancaria.
 * Contiene los datos necesarios para crear una cuenta, incluyendo el balance inicial.
 */
public class AccountRequestDTO {

    private Long clientId;
    private String accountKey;
    private Double initialBalance;
    private String accountType;
    private String bank;

    // Constructor
    public AccountRequestDTO() {
    }

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
