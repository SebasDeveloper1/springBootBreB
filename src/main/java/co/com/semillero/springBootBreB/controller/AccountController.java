package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.dto.AccountRequestDTO;
import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD relacionadas con las cuentas.
 * <p>
 * Utiliza las anotaciones de Spring:
 * - @RestController: Indica que la clase responde con JSON.
 * - @RequestMapping("/api/accounts"): Define el prefijo de la URL para las rutas.
 * - @Autowired: Inyecta el servicio AccountService para realizar las operaciones.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Crea una nueva cuenta en la base de datos a partir de un DTO de solicitud.
     *
     * @param accountRequestDTO DTO con los datos de la cuenta a crear, recibido en el cuerpo de la solicitud.
     * @return Cuenta creada y guardada en la base de datos.
     */
    @PostMapping
    public Account createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.createAccount(accountRequestDTO);
    }

    /**
     * Obtiene el listado de todas las cuentas.
     *
     * @return Lista de todas las cuentas.
     */
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    /**
     * Actualiza parcialmente una cuenta en la base de datos.
     * Solo los campos proporcionados en el DTO serán actualizados.
     *
     * @param accountId ID de la cuenta a actualizar.
     * @param accountRequestDTO DTO con los datos a actualizar.
     * @return Cuenta actualizada o null si no existe.
     */
    @PatchMapping("/{accountId}")
    public Account updateAccountPartial(@PathVariable Long accountId, @RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.updateAccountPartial(accountId, accountRequestDTO);
    }

    /**
     * Obtiene una cuenta por su ID desde el Header de la solicitud.
     *
     * @param accountId ID de la cuenta a buscar, recibido en el Header de la solicitud.
     * @return Cuenta encontrada o null si no existe.
     */
    @GetMapping("/details")
    public Account getAccount(@RequestHeader("Account-Id") Long accountId) {
        return accountService.getAccount(accountId);
    }

    /**
     * Elimina una cuenta de la base de datos por su ID.
     *
     * @param accountId ID de la cuenta a eliminar, obtenido de la ruta.
     */
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
