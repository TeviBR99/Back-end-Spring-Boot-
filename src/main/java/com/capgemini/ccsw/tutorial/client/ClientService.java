package com.capgemini.ccsw.tutorial.client;

import java.util.List;

import com.capgemini.ccsw.tutorial.client.model.Client;
import com.capgemini.ccsw.tutorial.client.model.ClientDto;

public interface ClientService {
    public List<Client> findAllClients();

    public void saveClient(Long id, ClientDto dto);

    public boolean nameNotEqual(String nameDto);

    public void deleteClient(Long id);

    public List<Client> findByName(String name);

    public Client getClientById(Long id);
}
