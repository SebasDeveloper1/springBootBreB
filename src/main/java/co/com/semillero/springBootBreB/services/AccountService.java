package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.dto.AccountRequestDTO;
import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con la entidad Cuenta.
 * <p>
 * Anotaciones:
 * - @Service: Indica que esta clase es un servicio de Spring que contiene la lógica de negocio relacionada
 * con las cuentas.
 * - @Autowired: Inyecta automáticamente una instancia del repositorio de cuentas para interactuar con la base de datos.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Crea una nueva cuenta en la base de datos a partir de un DTO de solicitud de cuenta.
     *
     * @param accountRequestDTO DTO con los datos de la cuenta a crear.
     * @return La cuenta creada y guardada en la base de datos.
     */
    public Account createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setClientId(accountRequestDTO.getClientId());
        account.setAccountKey(accountRequestDTO.getAccountKey());
        account.setBalance(accountRequestDTO.getInitialBalance());
        account.setAccountType(accountRequestDTO.getAccountType());
        account.setBank(accountRequestDTO.getBank());
        return accountRepository.save(account);
    }

    /**
     * Obtiene el listado de todas las cuentas en la base de datos.
     *
     * @return Lista de todas las cuentas.
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Obtiene una cuenta por su ID.
     *
     * @param accountId ID de la cuenta a buscar.
     * @return Cuenta encontrada o null si no existe.
     */
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    /**
     * Elimina una cuenta de la base de datos por su ID.
     *
     * @param accountId ID de la cuenta a eliminar.
     */
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
