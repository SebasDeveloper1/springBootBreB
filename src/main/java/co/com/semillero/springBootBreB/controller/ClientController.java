package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.entity.Client;
import co.com.semillero.springBootBreB.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los clientes.
 * <p>
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador de Spring que responde con JSON.
 * - @RequestMapping("/api/clients"): Define el prefijo de la URL para todas las rutas en este controlador.
 * - @Autowired: Inyecta automáticamente el servicio de cliente para interactuar con la lógica de negocio.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param client Objeto Cliente a crear, recibido en el cuerpo de la solicitud.
     * @return Cliente creado y guardado en la base de datos.
     */
    @PostMapping
    public Client createUser(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return Lista de clientes.
     */
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Obtiene un cliente por su ID desde el Header de la solicitud.
     *
     * @param clientId ID del cliente a buscar, recibido en el Header de la solicitud.
     * @return Cliente encontrado o null si no existe.
     */
    @GetMapping("/details")
    public Client getUser(@RequestHeader("Client-Id") Long clientId) {
        return clientService.getClient(clientId);
    }

    /**
     * Actualiza la información de un cliente existente.
     *
     * @param clientId ID del cliente a actualizar, obtenido de la ruta.
     * @param updatedClient Cliente con la información actualizada, recibido en el cuerpo de la solicitud.
     * @return Cliente actualizado y guardado en la base de datos o null si el cliente no existe.
     */
    @PutMapping("/{clientId}")
    public Client updateUser(@PathVariable Long clientId, @RequestBody Client updatedClient) {
        return clientService.updateClient(clientId, updatedClient);
    }

    /**
     * Actualiza parcialmente los datos de un cliente.
     *
     * @param clientId ID del cliente a actualizar.
     * @param updatedData Datos parciales del cliente a actualizar.
     * @return Cliente actualizado con los datos proporcionados.
     */
    @PatchMapping("/{clientId}")
    public Client updateClientPartially(@PathVariable Long clientId, @RequestBody Client updatedData) {
        return clientService.updateClientPartially(clientId, updatedData);
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     *
     * @param clientId ID del cliente a eliminar, obtenido de la ruta.
     */
    @DeleteMapping("/{clientId}")
    public void deleteUser(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }
}
