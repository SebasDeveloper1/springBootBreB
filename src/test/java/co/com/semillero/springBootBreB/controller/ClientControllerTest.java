package co.com.semillero.springBootBreB.controller;

import co.com.semillero.springBootBreB.entity.Client;
import co.com.semillero.springBootBreB.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService; // Simula el servicio para aislar las pruebas

    @InjectMocks
    private ClientController clientController; // Controlador que será probado

    private MockMvc mockMvc; // Objeto para simular las solicitudes HTTP
    private ObjectMapper objectMapper; // Funcion para convertir objetos en JSON

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Prueba para verificar que se pueda crear un cliente con el endpoint POST.
     */
    @Test
    void testCreateUser() throws Exception {
        Client client = new Client();
        client.setName("Ronald Luna");

        // Simula la respuesta del servicio
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        // Realiza la solicitud POST y verifica el resultado
        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client))) // Convierte el objeto en JSON
                .andExpect(status().isOk()) // Verifica que el código HTTP sea 200 OK
                .andExpect(jsonPath("$.name").value("Ronald Luna")); // Verifica que el nombre sea correcto

        verify(clientService, times(1)).createClient(any(Client.class)); // Verifica que el método del servicio fue llamado
    }

    /**
     * Prueba para obtener todos los clientes con el endpoint GET.
     */
    @Test
    void testGetAllClients() throws Exception {
        Client client1 = new Client();
        client1.setName("Ronald Luna");
        Client client2 = new Client();
        client2.setName("Sebastian Pedroza");

        // Simula la respuesta del servicio
        when(clientService.getAllClients()).thenReturn(Arrays.asList(client1, client2));

        // Realiza la solicitud GET y verifica el resultado
        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk()) // Verifica que el código HTTP sea 200 OK
                .andExpect(jsonPath("$.length()").value(2)) // Verifica que se retornen dos clientes
                .andExpect(jsonPath("$[0].name").value("Ronald Luna")) // Verifica el nombre del primer cliente
                .andExpect(jsonPath("$[1].name").value("Sebastian Pedroza")); // Verifica el nombre del segundo cliente

        verify(clientService, times(1)).getAllClients(); // Verifica que el método del servicio fue llamado
    }

    /**
     * Prueba para obtener un cliente específico con el endpoint GET.
     */
    @Test
    void testGetUser() throws Exception {
        Client client = new Client();
        client.setClientId(1L);
        client.setName("Ronald Luna");

        // Simula la respuesta del servicio
        when(clientService.getClient(1L)).thenReturn(client);

        // Realiza la solicitud GET y verifica el resultado
        mockMvc.perform(get("/api/clients/details")
                        .header("Client-Id", 1L)) // Incluye el ID del cliente en el header
                .andExpect(status().isOk()) // Verifica que el código HTTP sea 200 OK
                .andExpect(jsonPath("$.name").value("Ronald Luna")); // Verifica el nombre del cliente

        verify(clientService, times(1)).getClient(1L); // Verifica que el método del servicio fue llamado
    }

    /**
     * Prueba para actualizar un cliente con el endpoint PUT.
     */
    @Test
    void testUpdateUser() throws Exception {
        Client updatedClient = new Client();
        updatedClient.setName("Ronald Actualizado");
        updatedClient.setEmail("ronaldactualiado@mail.com");

        // Simula la respuesta del servicio
        when(clientService.updateClient(eq(1L), any(Client.class))).thenReturn(updatedClient);

        // Realiza la solicitud PUT y verifica el resultado
        mockMvc.perform(put("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk()) // Verifica que el código HTTP sea 200 OK
                .andExpect(jsonPath("$.name").value("Ronald Actualizado")) // Verifica que el nombre se actualizó
                .andExpect(jsonPath("$.email").value("ronaldactualiado@mail.com")); // Verifica que el email se actualizó

        verify(clientService, times(1)).updateClient(eq(1L), any(Client.class)); // Verifica que el método del servicio fue llamado
    }

    /**
     * Prueba para eliminar un cliente con el endpoint DELETE.
     */
    @Test
    void testDeleteUser() throws Exception {
        // Simula la respuesta del servicio
        doNothing().when(clientService).deleteClient(1L);

        // Realiza la solicitud DELETE y verifica el resultado
        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isOk()); // Verifica que el código HTTP sea 200 OK

        verify(clientService, times(1)).deleteClient(1L); // Verifica que el método del servicio fue llamado
    }
}
