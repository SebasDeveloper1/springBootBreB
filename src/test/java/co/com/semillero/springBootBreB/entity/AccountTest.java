package co.com.semillero.springBootBreB.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class AccountTest {

    @Test
    void testAccountGettersAndSetters() {
        //Se realiza la isntancia de Account
        Account account = new Account();

        // Establecer valores
        account.setAccountId(1L);
        account.setClientId(1L);
        account.setAccountKey("3001234567");
        account.setBalance(50000.0);
        account.setAccountType("Ahorros");
        account.setBank("BBVA");

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setModificationDate(now);

        // Verificar que los valores estén creados correctamente
        assertEquals(1L, account.getAccountId());
        assertEquals(1L, account.getClientId());
        assertEquals("3001234567", account.getAccountKey());
        assertEquals(50000.0, account.getBalance());
        assertEquals("Ahorros", account.getAccountType());
        assertEquals("BBVA", account.getBank());
        assertEquals(now, account.getCreationDate());
        assertEquals(now, account.getModificationDate());
    }

    @Test
    void testClientModificationDates() {
        // Se realizza una instancia de Account
        Account account = new Account();

        // Establecer y verificar fechas
        LocalDateTime creationTime = LocalDateTime.now();
        account.setCreationDate(creationTime);

        LocalDateTime modificationTime = creationTime.plusDays(1);
        account.setModificationDate(modificationTime);

        assertEquals(creationTime, account.getCreationDate());
        assertEquals(modificationTime, account.getModificationDate());
    }

    @Test
    void testNullValues() {
        // // Se realizza una instancia de Account sin establecer valores
        Account account = new Account();

        // Verificar que los valores iniciales sean nulos
        assertNull(account.getAccountId());
        assertNull(account.getClientId());
        assertNull(account.getAccountKey());
        assertNull(account.getBalance());
        assertNull(account.getAccountType());
        assertNull(account.getBank());
        assertNull(account.getCreationDate());
        assertNull(account.getModificationDate());
    }

}