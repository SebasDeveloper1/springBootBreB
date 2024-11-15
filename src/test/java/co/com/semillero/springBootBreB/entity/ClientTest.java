package co.com.semillero.springBootBreB.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientGettersAndSetters() {
        // Crear una instancia de Client
        Client client = new Client();

        // Establecer valores
        client.setClientId(1L);
        client.setName("Sebastian");
        client.setLastName("Pedroza");
        client.setDocumentType("CC");
        client.setDocumentNumber("1234567890");
        client.setEmail("sebastianpedroza@mail.com");
        client.setPhone("3214567890");

        LocalDateTime now = LocalDateTime.now();
        client.setCreationDate(now);
        client.setModificationDate(now);

        // Verificar que los valores estén creados correctamente
        assertEquals(1L, client.getClientId());
        assertEquals("Sebastian", client.getName());
        assertEquals("Pedroza", client.getLastName());
        assertEquals("CC", client.getDocumentType());
        assertEquals("1234567890", client.getDocumentNumber());
        assertEquals("sebastianpedroza@mail.com", client.getEmail());
        assertEquals("3214567890", client.getPhone());
        assertEquals(now, client.getCreationDate());
        assertEquals(now, client.getModificationDate());
    }

    @Test
    void testClientModificationDates() {
        // Crear una instancia de Client
        Client client = new Client();

        // Establecer y verificar fechas
        LocalDateTime creationTime = LocalDateTime.now();
        client.setCreationDate(creationTime);

        LocalDateTime modificationTime = creationTime.plusDays(1);
        client.setModificationDate(modificationTime);

        assertEquals(creationTime, client.getCreationDate());
        assertEquals(modificationTime, client.getModificationDate());
    }

    @Test
    void testNullValues() {
        // Crear una instancia de Client sin establecer valores
        Client client = new Client();

        // Verificar que los valores iniciales sean nulos
        assertNull(client.getClientId());
        assertNull(client.getName());
        assertNull(client.getLastName());
        assertNull(client.getDocumentType());
        assertNull(client.getDocumentNumber());
        assertNull(client.getEmail());
        assertNull(client.getPhone());
        assertNull(client.getCreationDate());
        assertNull(client.getModificationDate());
    }
}
