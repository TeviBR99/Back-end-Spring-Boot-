package com.capgemini.ccsw.tutorial.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.client.model.Client;
import com.capgemini.ccsw.tutorial.client.model.ClientDto;

@Service
public class ClientServiceImpl implements ClientService {

    // Inyects here
    @Autowired
    ClientRepository clientRepository;

    // Methods here
    @Override
    public List<Client> findAllClients() {
        // TODO Auto-generated method stub
        return (List<Client>) this.clientRepository.findAll();
    }

    public void saveClient(Long id, ClientDto dto) {
        Client client = null;

        if (nameNotEqual(dto.getName())) {
            if (id != null) {
                client = clientRepository.findById(id).orElse(null);
            } else
                client = new Client();

            client.setName(dto.getName());
            this.clientRepository.save(client);
        }

    }

    /*
     * nameNotEqual: -si devuelve true, es porque no existe otro cliente con el
     * mismo nombre -si devuelve false es porque sí que existe un cliente con ese
     * nombre.
     */
    @Override
    public boolean nameNotEqual(String nameDto) {

        boolean result = true;

        List<Client> clientList = new ArrayList<Client>();
        clientList = this.clientRepository.findClientByName(nameDto);

        if (clientList.size() > 0)
            result = false;

        return result;
    }

    @Override
    public void deleteClient(Long id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findByName(String name) {
        // TODO Auto-generated method stub
        return (List<Client>) this.clientRepository.findClientByName(name);
    }

    @Override
    public Client getClientById(Long id) {
        // TODO Auto-generated method stub
        return this.clientRepository.findById(id).orElse(null);
    }

}
