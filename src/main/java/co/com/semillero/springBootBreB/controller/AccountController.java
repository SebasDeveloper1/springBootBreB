package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.entity.Account;
import co.com.semillero.springBootBreB.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las operaciones relacionadas con las cuentas.
 * <p>
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador de Spring que responde con JSON.
 * - @RequestMapping("/api/accounts"): Define el prefijo de la URL para todas las rutas en este controlador.
 * - @Autowired: Inyecta automáticamente el servicio de cuentas para interactuar con la lógica de negocio.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Crea una nueva cuenta en la base de datos.
     *
     * @param account Objeto Cuenta a crear, recibido en el cuerpo de la solicitud.
     * @return Cuenta creada y guardada en la base de datos.
     */
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Obtiene una cuenta por su ID.
     *
     * @param accountId ID de la cuenta a buscar, obtenido de la ruta.
     * @return Cuenta encontrada o null si no existe.
     */
    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
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
