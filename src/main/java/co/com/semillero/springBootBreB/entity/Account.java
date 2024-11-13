package co.com.semillero.springBootBreB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Representa una entidad de Cuenta en la aplicación, la cual contiene la
 * información de las cuentas bancarias de los usuarios, incluyendo datos de
 * saldo, tipo de cuenta, y el banco asociado.
 * <p>
 * Anotaciones:
 * - @Entity: Marca esta clase como una entidad de JPA, que será mapeada a
 * una tabla de base de datos.
 * - @Id: Define el atributo que actúa como clave primaria.
 * - @GeneratedValue: Establece la estrategia de generación de la clave
 * primaria, en este caso, Identity.
 * - @CreationTimestamp: Marca el momento de creación del registro.
 * - @UpdateTimestamp: Marca el momento de modificación del registro.
 */
@Entity
public class Account {

    /**
     * Identificador único de la cuenta, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    /**
     * Identificador del cliente asociado a la cuenta.
     */
    private Long clientId;

    /**
     * Llave o Alias de la cuenta.
     */
    private String accountKey;

    /**
     * Saldo actual de la cuenta.
     */
    private Double balance;

    /**
     * Tipo de cuenta (ej., ahorro, corriente).
     */
    private String accountType;

    /**
     * Banco asociado a la cuenta.
     */
    private String bank;

    /**
     * Fecha de creación del registro, asignada automáticamente.
     */
    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * Fecha de la última modificación del registro, actualizada automáticamente.
     */
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    // Getters y setters

    /**
     * Obtiene el ID de la cuenta.
     *
     * @return accountId ID de la cuenta.
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Establece el ID de la cuenta.
     *
     * @param accountId ID de la cuenta.
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Obtiene el ID del usuario asociado a la cuenta.
     *
     * @return clientId ID del cliente asociado.
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * Establece el ID del usuario asociado a la cuenta.
     *
     * @param clientId ID del cliente asociado.
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Obtiene la clave de la cuenta.
     *
     * @return accountKey Clave de la cuenta.
     */
    public String getAccountKey() {
        return accountKey;
    }

    /**
     * Establece la clave de la cuenta.
     *
     * @param accountKey Clave de la cuenta.
     */
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    /**
     * Obtiene el saldo de la cuenta.
     *
     * @return balance Saldo de la cuenta.
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Establece el saldo de la cuenta.
     *
     * @param balance Saldo de la cuenta.
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Obtiene el tipo de cuenta.
     *
     * @return accountType Tipo de cuenta.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Establece el tipo de cuenta.
     *
     * @param accountType Tipo de cuenta.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Obtiene el banco asociado a la cuenta.
     *
     * @return bank Banco asociado.
     */
    public String getBank() {
        return bank;
    }

    /**
     * Establece el banco asociado a la cuenta.
     *
     * @param bank Banco asociado.
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * Obtiene la fecha de creación del registro.
     *
     * @return creationDate Fecha de creación del registro.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Establece la fecha de creación del registro.
     *
     * @param creationDate Fecha de creación del registro.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Obtiene la fecha de la última modificación del registro.
     *
     * @return modificationDate Fecha de la última modificación.
     */
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    /**
     * Establece la fecha de la última modificación del registro.
     *
     * @param modificationDate Fecha de la última modificación.
     */
    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
