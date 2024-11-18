package co.com.semillero.springBootBreB.dto;

import co.com.semillero.springBootBreB.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRequestDTOTest {

    @Test
    void testAccountRequestDTO() {

        //Se realiza la isntancia deAccountRequestDTO
        AccountRequestDTO accountRequestDto = new AccountRequestDTO();

        AccountRequestDTO accountRequestDto2 = new AccountRequestDTO(1L,"3001234567",50000.0,"Ahorros","BBVA");

        // Establecer valores
        accountRequestDto.setClientId(1L);
        accountRequestDto.setAccountKey("3001234567");
        accountRequestDto.setInitialBalance(50000.0);
        accountRequestDto.setAccountType("Ahorros");
        accountRequestDto.setBank("BBVA");

        // Verificar que los valores estén creados correctamente
        assertEquals(1L, accountRequestDto.getClientId());
        assertEquals("3001234567",accountRequestDto.getAccountKey());
        assertEquals(50000.0, accountRequestDto.getInitialBalance());
        assertEquals("Ahorros", accountRequestDto.getAccountType());
        assertEquals("BBVA", accountRequestDto.getBank());
    }

    @Test
    void testNullValues() {
        // // Se realizza una instancia de ccountRequestDTO sin establecer valores
        AccountRequestDTO accountRequestDto = new AccountRequestDTO();

        // Verificar que los valores iniciales sean nulos
        assertNull(accountRequestDto.getClientId());
        assertNull(accountRequestDto.getAccountKey());
        assertNull(accountRequestDto.getInitialBalance());
        assertNull(accountRequestDto.getAccountType());
        assertNull(accountRequestDto.getBank());
    }
}