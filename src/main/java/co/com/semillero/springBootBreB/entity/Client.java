package co.com.semillero.springBootBreB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Representa una entidad de Cliente en la aplicación, la cual contiene la
 * información básica de un cliente y marcas de tiempo de creación y modificación.
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
public class Client {

    /**
     * Identificador único del cliente, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    /**
     * Nombre del cliente.
     */
    private String name;

    /**
     * Apellido del cliente.
     */
    private String lastName;

    /**
     * Tipo de documento de identificación del cliente (ej., "CC": cédula).
     */
    private String documentType;

    /**
     * Número del documento de identificación del cliente.
     */
    private String documentNumber;

    /**
     * Correo electrónico del cliente.
     */
    private String email;

    /**
     * Número de teléfono del cliente.
     */
    private String phone;

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
     * Obtiene el ID del cliente.
     *
     * @return clientId ID del cliente.
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param clientId ID del cliente.
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return name Nombre del cliente.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param name Nombre del cliente.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido del cliente.
     *
     * @return lastName Apellido del cliente.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del cliente.
     *
     * @param lastName Apellido del cliente.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el tipo de documento de identificación del cliente.
     *
     * @return documentType Tipo de documento de identificación.
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Establece el tipo de documento de identificación del cliente.
     *
     * @param documentType Tipo de documento de identificación.
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * Obtiene el número de documento de identificación del cliente.
     *
     * @return documentNumber Número de documento de identificación.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Establece el número de documento de identificación del cliente.
     *
     * @param documentNumber Número de documento de identificación.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return email Correo electrónico del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param email Correo electrónico del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono de contacto del cliente.
     *
     * @return phone Número de teléfono del cliente.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono de contacto del cliente.
     *
     * @param phone Número de teléfono del cliente.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
