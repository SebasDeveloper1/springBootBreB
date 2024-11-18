package co.com.semillero.springBootBreB.services;

import co.com.semillero.springBootBreB.entity.Client;
import co.com.semillero.springBootBreB.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    // Simula la dependencia del repositorio
    @Mock
    private ClientRepository clientRepository;

    // Instancia del servicio, con las dependencias simuladas inyectadas
    @InjectMocks
    private ClientService clientService;

    // Inicializa los mocks antes de cada prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Configura el contexto de Mockito
    }

    // Prueba para el método que crea un cliente
    @Test
    void testCreateClient() {
        // Datos de entrada
        Client client = new Client();
        // Establecer valores
        client.setClientId(1L);
        client.setName("Sebastian");
        client.setLastName("Pedroza");
        client.setDocumentType("CC");
        client.setDocumentNumber("1234567890");
        client.setEmail("sebastianpedroza@mail.com");
        client.setPhone("3214567890");

        // Simulación: cuando se guarda el cliente, devuelve el mismo objeto
        when(clientRepository.save(client)).thenReturn(client);

        // Llamada al método a probar
        Client createdClient = clientService.createClient(client);

        // Verificaciones
        assertNotNull(createdClient); // El cliente creado no debe ser null
        assertEquals("Sebastian", createdClient.getName()); // El nombre debe coincidir
        assertEquals("1234567890", createdClient.getDocumentNumber()); // El nombre debe coincidir
        verify(clientRepository, times(1)).save(client); // Verifica que save() se llamó una vez
    }

    // Prueba para obtener todos los clientes
    @Test
    void testGetAllClients() {
        // Datos simulados
        Client client1 = new Client();
        client1.setClientId(1L);
        client1.setName("Sebastian");
        client1.setLastName("Pedroza");
        client1.setDocumentType("CC");
        client1.setDocumentNumber("1234567890");
        client1.setEmail("sebastianpedroza@mail.com");
        client1.setPhone("3214567890");
        Client client2 = new Client();
        client2.setClientId(2L);
        client2.setName("Ronald");
        client2.setLastName("Luna");
        client2.setDocumentType("CC");
        client2.setDocumentNumber("0987654321");
        client2.setEmail("ronaldluna@mail.com");
        client2.setPhone("0987654231");
        // Simulación: el repositorio devuelve una lista de clientes
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        // Llamada al método a probar
        var clientList = clientService.getAllClients();

        // Verificaciones
        assertEquals(2, clientList.size()); // Verifica que se obtuvieron dos clientes
        verify(clientRepository, times(1)).findAll(); // Verifica que findAll() se llamó una vez
    }

    // Prueba para obtener un cliente por ID
    @Test
    void testGetClient() {
        // Cliente simulado
        Client client = new Client();
        client.setClientId(2L);
        client.setName("Ronald");
        client.setLastName("Luna");
        client.setDocumentType("CC");
        client.setDocumentNumber("0987654321");
        client.setEmail("ronaldluna@mail.com");
        client.setPhone("0987654231");

        // Simulación: el repositorio devuelve un cliente cuando se busca por ID
        when(clientRepository.findById(2L)).thenReturn(Optional.of(client));

        // Llamada al método a probar
        Client retrievedClient = clientService.getClient(2L);

        // Verificaciones
        assertNotNull(retrievedClient); // El cliente recuperado no debe ser null
        assertEquals("Ronald", retrievedClient.getName()); // Verifica que el nombre coincide
        assertEquals(2L, retrievedClient.getClientId()); // Verifica que el ID coincide
        verify(clientRepository, times(1)).findById(2L); // Verifica que findById() se llamó una vez
    }

    // Prueba para actualizar un cliente completamente
    @Test
    void testUpdateClient() {
        // Cliente existente
        Client existingClient = new Client();
        existingClient.setClientId(1L);
        existingClient.setName("Sebastian");
        existingClient.setLastName("Pedroza");
        existingClient.setDocumentType("CC");
        existingClient.setDocumentNumber("1234567890");
        existingClient.setEmail("sebastianpedroza@mail.com");
        existingClient.setPhone("3214567890");

        // Datos actualizados
        Client updatedClient = new Client();
        updatedClient.setName("Sebastian actualizado");
        existingClient.setLastName("Pedroza");
        existingClient.setDocumentType("CC");
        existingClient.setDocumentNumber("1234567890");
        updatedClient.setEmail("sebastianactualizado@mail.com");
        existingClient.setPhone("3214567890");

        // Simulaciones
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        // Llamada al método a probar
        Client result = clientService.updateClient(1L, updatedClient);

        // Verificaciones
        assertNotNull(result); // El cliente actualizado no debe ser nulo
        assertEquals("Sebastian actualizado", result.getName()); // Verifica que el nombre se actualizó
        assertEquals("sebastianactualizado@mail.com", result.getEmail()); // Verifica que el email se actualizó
        verify(clientRepository, times(1)).findById(1L); // Verifica que findById() se llamó
        verify(clientRepository, times(1)).save(existingClient); // Verifica que save() se llamó
    }

    // Prueba para actualizar un cliente cuando es null
    @Test
    void testUpdateClientNull() {
        // Simulación: No se encuentra el cliente en el repositorio
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamada al método a probar
        Client result = clientService.updateClient(1L, new Client());

        // Verificación: El resultado debe ser nulo porque el cliente no existe
        assertNull(result);
    }

    // Prueba para actualizar PARCIALMENTE un cliente (Todos los campos)
    @Test
    void testUpdateClientPartially_AllFieldsUpdated() {
        // Cliente existente
        Client existingClient = new Client();
        existingClient.setClientId(2L);
        existingClient.setName("Ronald");
        existingClient.setEmail("ronald@mail.com");

        // Datos para actualización parcial
        Client updatedClient = new Client();
        updatedClient.setDocumentType("CC");
        updatedClient.setDocumentNumber("1234567890");
        updatedClient.setName("Ronald Actualizado");
        updatedClient.setLastName("Luna");
        updatedClient.setEmail("ronaldactualizado@mail.com");
        updatedClient.setPhone("9876543210");
        // Simulación
        when(clientRepository.findById(2L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        // Llamada al método
        Client result = clientService.updateClientPartially(2L, updatedClient);

        // Verificaciones
        assertNotNull(result);
        assertEquals("CC", result.getDocumentType());
        assertEquals("1234567890", result.getDocumentNumber());
        assertEquals("Ronald Actualizado", result.getName());
        assertEquals("Luna", result.getLastName());
        assertEquals("ronaldactualizado@mail.com", result.getEmail());
        assertEquals("9876543210", result.getPhone());
        verify(clientRepository, times(1)).findById(2L);
        verify(clientRepository, times(1)).save(existingClient);
    }

    // Prueba para actualizar PARCIALMENTE un cliente (Todos los campos)
    @Test
    void testUpdateClientPartially_SomeFieldsUpdated() {
        // Cliente existente
        Client existingClient = new Client();
        existingClient.setClientId(1L);
        existingClient.setName("Sebastian");
        existingClient.setEmail("sebastian@mail.com");

        // Datos para actualización parcial
        Client partialUpdate = new Client();
        partialUpdate.setEmail("sebastianactualizado@mail.com");
        partialUpdate.setPhone("1234567890");

        // Simulación
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        // Llamada al método
        Client result = clientService.updateClientPartially(1L, partialUpdate);

        // Verificaciones
        assertNotNull(result);
        assertEquals("Sebastian", result.getName()); // El nombre no cambia
        assertEquals("sebastianactualizado@mail.com", result.getEmail()); // El email se actualiza
        assertEquals("1234567890", result.getPhone()); // El teléfono se actualiza
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(existingClient);
    }

    // Prueba para eliminar un cliente
    @Test
    void testDeleteClient() {
        // Simulación: no se hace nada cuando se elimina un cliente por ID
        doNothing().when(clientRepository).deleteById(1L);

        // Llamada al método a probar
        clientService.deleteClient(1L);

        // Verificaciones
        verify(clientRepository, times(1)).deleteById(1L); // Verifica que deleteById() se llamó una vez
    }
}