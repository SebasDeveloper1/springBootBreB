package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.dto.AccountRequestDTO;
import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private AccountRequestDTO accountRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks
        accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setClientId(1L);
        accountRequestDTO.setAccountKey("3001234567");
        accountRequestDTO.setInitialBalance(1000.0);
        accountRequestDTO.setAccountType("Ahorros");
        accountRequestDTO.setBank("BBVA");
    }

    @Test
    void testCreateAccount() {
        // Preparar el mock para el método `save`
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setClientId(accountRequestDTO.getClientId());
        mockAccount.setAccountKey(accountRequestDTO.getAccountKey());
        mockAccount.setBalance(accountRequestDTO.getInitialBalance());
        mockAccount.setAccountType(accountRequestDTO.getAccountType());
        mockAccount.setBank(accountRequestDTO.getBank());

        when(accountRepository.save(any(Account.class))).thenReturn(mockAccount);

        // Ejecutar el método createAccount
        Account createdAccount = accountService.createAccount(accountRequestDTO);

        // Verificar que el repositorio fue llamado y los resultados sean correctos
        verify(accountRepository, times(1)).save(any(Account.class));
        assertNotNull(createdAccount);
        assertEquals(accountRequestDTO.getClientId(), createdAccount.getClientId());
        assertEquals(accountRequestDTO.getAccountKey(), createdAccount.getAccountKey());
        assertEquals(accountRequestDTO.getInitialBalance(), createdAccount.getBalance());
        assertEquals(accountRequestDTO.getAccountType(), createdAccount.getAccountType());
        assertEquals(accountRequestDTO.getBank(), createdAccount.getBank());
    }

    @Test
    void testGetAllAccounts() {
        // Preparar el mock para el método `findAll`
        Account account1 = new Account();
        account1.setAccountId(1L);
        account1.setClientId(1L);
        account1.setAccountKey("3001234567");
        account1.setBalance(1000.0);
        account1.setAccountType("Ahorros");
        account1.setBank("BBVA");

        Account account2 = new Account();
        account2.setAccountId(2L);
        account2.setClientId(2L);
        account2.setAccountKey("2111234789");
        account2.setBalance(2000.0);
        account2.setAccountType("Ahorros");
        account2.setBank("Bancolombia");

        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

        // Ejecutar el método getAllAccounts
        List<Account> accounts = accountService.getAllAccounts();

        // Verificar que el repositorio fue llamado y que se devuelven las cuentas correctas
        verify(accountRepository, times(1)).findAll();
        assertFalse(accounts.isEmpty());
        assertEquals(2, accounts.size());
    }

    @Test
    void testGetAccount() {
        // Preparar el mock para el método 'findById'
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setClientId(1L);
        mockAccount.setAccountKey("3001234567");
        mockAccount.setBalance(1000.0);
        mockAccount.setAccountType("Ahorros");
        mockAccount.setBank("BBVA");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));

        // Ejecutar el método getAccount
        Account account = accountService.getAccount(1L);

        // Verificar que el repositorio fue llamado y la cuenta se devuelva correctamente
        verify(accountRepository, times(1)).findById(1L);
        assertNotNull(account);
        assertEquals(1L, account.getAccountId());
    }

    @Test
    void testUpdateAccountPartial() {
        // Crear una cuenta mock para el test
        Account existingAccount = new Account();
        existingAccount.setAccountId(1L);
        existingAccount.setClientId(1L);
        existingAccount.setAccountKey("3001234567");
        existingAccount.setBalance(1000.0);
        existingAccount.setAccountType("Ahorros");
        existingAccount.setBank("BBVA");

        AccountRequestDTO updateRequestDTO = new AccountRequestDTO();
        updateRequestDTO.setInitialBalance(20000.0);
        updateRequestDTO.setAccountKey("3001234567");
        updateRequestDTO.setAccountType("Ahorros");
        updateRequestDTO.setBank("BBVA");


        when(accountRepository.findById(1L)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        // Ejecutar el método updateAccountPartial
        Account updatedAccount = accountService.updateAccountPartial(1L, updateRequestDTO);

        // Verificar que se actualizaron los campos correctamente
        assertNotNull(updatedAccount);
        assertEquals("3001234567", updatedAccount.getAccountKey());
        assertEquals(20000.0, updatedAccount.getBalance());
        assertEquals("Ahorros", updatedAccount.getAccountType());
        assertEquals("BBVA", updatedAccount.getBank());
        assertEquals(existingAccount.getClientId(), updatedAccount.getClientId());  // No cambia el clientId
    }

    @Test
    void testUpdateAccountPartialNotFound() {
        // Crear un DTO de actualización
        AccountRequestDTO updateRequestDTO = new AccountRequestDTO();
        updateRequestDTO.setInitialBalance(50000.0);

        // Simular que la cuenta no existe
        when(accountRepository.findById(3L)).thenReturn(Optional.empty());

        // Ejecutar el método updateAccountPartial
        Account updatedAccount = accountService.updateAccountPartial(3L, updateRequestDTO);

        // Verificar que el resultado es nulo
        assertNull(updatedAccount);
    }

    @Test
    void testDeleteAccount() {
        // Crear una cuenta mock
        Account account = new Account();
        account.setAccountId(1L);

        // Simular que la cuenta existe
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Ejecutar el metodo deleteAccount
        accountService.deleteAccount(1L);

        // Verificar que el repositorio fue llamado para eliminar la cuenta
        verify(accountRepository, times(1)).deleteById(1L);
    }
}