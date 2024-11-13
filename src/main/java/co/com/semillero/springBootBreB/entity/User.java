package co.com.semillero.springBootBreB.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Representa una entidad de Usuario en la aplicación, la cual contiene la
 * información básica de un usuario y marcas de tiempo de creación y modificación.
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
public class User {

    /**
     * Identificador único del usuario, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * Nombre del usuario.
     */
    private String name;

    /**
     * Apellido del usuario.
     */
    private String lastName;

    /**
     * Tipo de documento de identificación del usuario (ej., "CC": cédula).
     */
    private String documentType;

    /**
     * Número del documento de identificación del usuario.
     */
    private String documentNumber;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Número de teléfono del usuario.
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
     * Obtiene el ID del usuario.
     *
     * @return userId ID del usuario.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param userId ID del usuario.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return name Nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name Nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return lastName Apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param lastName Apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el tipo de documento de identificación del usuario.
     *
     * @return documentType Tipo de documento de identificación.
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Establece el tipo de documento de identificación del usuario.
     *
     * @param documentType Tipo de documento de identificación.
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * Obtiene el número de documento de identificación del usuario.
     *
     * @return documentNumber Número de documento de identificación.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Establece el número de documento de identificación del usuario.
     *
     * @param documentNumber Número de documento de identificación.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return email Correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email Correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono de contacto del usuario.
     *
     * @return phone Número de teléfono del usuario.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono de contacto del usuario.
     *
     * @param phone Número de teléfono del usuario.
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
