package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.dto.AccountRequestDTO;
import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testCreateAccount() throws Exception {
        // Arrange
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1L, "3001234567", 50000.0, "Ahorros", "BBVA");
        Account account = new Account(1L, "3001234567", 50000.0, "Ahorros", "BBVA");

        when(accountService.createAccount(any(AccountRequestDTO.class))).thenReturn(account);

        // Act & Assert
        mockMvc.perform(post("/api/accounts")
                        .contentType("application/json")
                        .content("{\"clientId\": 1, \"accountKey\": \"3001234567\", \"initialBalance\": 50000, \"accountType\": \"Ahorros\", \"bank\": \"BBVA\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.accountKey").value("3001234567"))
                .andExpect(jsonPath("$.initialBalance").value(50000))
                .andExpect(jsonPath("$.accountType").value("Ahorros"))
                .andExpect(jsonPath("$.bank").value("BBVA"));
    }

    @Test
    void testGetAllAccounts() throws Exception {
        // Arrange
        Account account1 = new Account(1L, "3001234567", 50000.0, "Ahorros", "BBVA");
        Account account2 = new Account(2L, "3001234567", 50000.0, "Ahorros", "BBVA");
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        // Act & Assert
        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientId").value(1))
                .andExpect(jsonPath("$[1].clientId").value(2))
                .andExpect(jsonPath("$[0].accountKey").value("3001234567"))
                .andExpect(jsonPath("$[1].accountKey").value("3001234567"))
                .andExpect(jsonPath("$[0].initialBalance").value(20000.0))
                .andExpect(jsonPath("$[1].initialBalance").value(30000.0))
                .andExpect(jsonPath("$[0].accountType").value("Ahorros"))
                .andExpect(jsonPath("$[1].accountType").value("Ahorros"))
                .andExpect(jsonPath("$[0].bank").value("BBVA"))
                .andExpect(jsonPath("$[1].bank").value("BBVA"));


    }

    @Test
    void testUpdateAccountPartial() throws Exception {
        // Arrange
        Long accountId = 1L;
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO("Updated Name", "updated@example.com");
        Account account = new Account(accountId, "Updated Name", "updated@example.com");

        when(accountService.updateAccountPartial(eq(accountId), any(AccountRequestDTO.class))).thenReturn(account);

        // Act & Assert
        mockMvc.perform(patch("/api/accounts/{accountId}", accountId)
                        .contentType("application/json")
                        .content("{\"name\": \"Updated Name\", \"email\": \"updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    void testGetAccount() throws Exception {
        // Arrange
        Long accountId = 1L;
        Account account = new Account(accountId, "John Doe", "john@example.com");

        when(accountService.getAccount(eq(accountId))).thenReturn(account);

        // Act & Assert
        mockMvc.perform(get("/api/accounts/details")
                        .header("Account-Id", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testDeleteAccount() throws Exception {
        // Arrange
        Long accountId = 1L;

        doNothing().when(accountService).deleteAccount(eq(accountId));

        // Act & Assert
        mockMvc.perform(delete("/api/accounts/{accountId}", accountId))
                .andExpect(status().isNoContent());

        // Verificar que el método de eliminación fue llamado
        verify(accountService, times(1)).deleteAccount(eq(accountId));
    }
}