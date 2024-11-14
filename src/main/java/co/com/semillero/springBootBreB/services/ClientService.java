package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.entity.Client;
import co.com.semillero.springBootBreB.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las operaciones relacionadas con la entidad Cliente.
 * <p>
 * Anotaciones:
 * - @Service: Indica que esta clase es un servicio de Spring, donde se implementa la lógica de negocio relacionada
 * con los clientes.
 * - @Autowired: Inyecta automáticamente una instancia del repositorio de cliente para interactuar con la base de datos.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param client Cliente a crear.
     * @return El cliente creado y guardado en la base de datos.
     */
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return Lista de clientes.
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param clientId ID del cliente a buscar.
     * @return Cliente encontrado o null si no existe.
     */
    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    /**
     * Actualiza la información de un cliente existente.
     *
     * @param clientId       ID del cliente a actualizar.
     * @param updatedClient  Cliente con la información actualizada.
     * @return Cliente actualizado y guardado en la base de datos o null si el cliente no existe.
     */
    public Client updateClient(Long clientId, Client updatedClient) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            client.setName(updatedClient.getName());
            client.setLastName(updatedClient.getLastName());
            client.setEmail(updatedClient.getEmail());
            client.setPhone(updatedClient.getPhone());
            return clientRepository.save(client);
        }
        return null;
    }

    /**
     * Actualiza parcialmente los datos de un cliente existente.
     *
     * @param clientId    ID del cliente a actualizar.
     * @param updatedData Datos parciales del cliente a actualizar.
     * @return Cliente actualizado o null si no existe.
     */
    public Client updateClientPartially(Long clientId, Client updatedData) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            // Solo se actualizan los campos no nulos que se pasan
            if (updatedData.getName() != null) {
                client.setName(updatedData.getName());
            }
            if (updatedData.getLastName() != null) {
                client.setLastName(updatedData.getLastName());
            }
            if (updatedData.getEmail() != null) {
                client.setEmail(updatedData.getEmail());
            }
            if (updatedData.getPhone() != null) {
                client.setPhone(updatedData.getPhone());
            }

            // Guardar el cliente con los campos actualizados
            return clientRepository.save(client);
        }

        return null;  // Si el cliente no existe
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     *
     * @param clientId ID del cliente a eliminar.
     */
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
