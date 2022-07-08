package com.capgemini.ccsw.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capgemini.ccsw.tutorial.client.ClientRepository;
import com.capgemini.ccsw.tutorial.client.ClientServiceImpl;
import com.capgemini.ccsw.tutorial.client.model.Client;
import com.capgemini.ccsw.tutorial.client.model.ClientDto;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks // Aquí debemos de indicar la clase implementada
    private ClientServiceImpl clientService;

    @Test
    public void findAllClientsShouldReturnAllClients() {

        // Definimos una lista mockeada de clientes
        List<Client> list = new ArrayList<>();

        // TEnemos que simular el número de clientes en nuestra base de datos, ya que no
        // estamos levantando la aplicación
        list.add(mock(Client.class));
        list.add(mock(Client.class));
        list.add(mock(Client.class));
        list.add(mock(Client.class));

        // Cuando el repositorio realice la consulta select * from Clientes, nos
        // devolverá una lista. Si no funciona, devuelve una RunTimeException
        when(clientRepository.findAll()).thenReturn(list);

        // Simulamos que el Service nos devuelve la información correcta
        // RECORDAR QUE...
        // El método que queremos testear, existe actualmente en la clase ClientService
        List<Client> clients = clientService.findAllClients();

        // Comprobamos que los resultados devueltos por el service no son nulos
        assertNotNull(clients);

        // Comprobamos que el tamaño del array de clientes devueltos, sea igual al
        // número de elementos que hemos registrado en nuestra
        // base de datos (mirar data.sql) (En este caso es igual a 4)
        assertEquals(4, clients.size());
    }

    public static final String CLIENT_NAME = "Nuevo cliente";

    @Test
    public void saveWithoutIdShouldInsertClient() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        // ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class);
        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);
        clientService.saveClient(null, clientDto);

        verify(clientRepository).save(client.capture());

        assertEquals(CLIENT_NAME, client.getValue().getName());
    }

    public static final Long EXISTS_CLIENT_ID = 1L;

    @Test
    public void saveIfExistsIdShouldUpdateClient() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);
        /*
         * Category category = mock(Category.class);
         * when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(
         * category));
         */
        Client client = mock(Client.class);
        Client clientAux = mock(Client.class);

        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));
        if (clientRepository.findClientByName(CLIENT_NAME) == null)
            clientService.saveClient(EXISTS_CLIENT_ID, clientDto);

        verify(clientRepository).save(client);
    }

}
