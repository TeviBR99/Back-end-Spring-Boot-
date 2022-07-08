package com.capgemini.ccsw.tutorial.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.tutorial.client.model.ClientDto;
import com.capgemini.ccsw.tutorial.config.mapper.BeanMapper;

@RequestMapping(value = "/client")
@RestController
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    BeanMapper beanMapper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientDto> findClientes() {

        // List<ClientDto> clients = new ArrayList<ClientDto>();

        return this.beanMapper.mapList(clientService.findAllClients(), ClientDto.class);
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto dto) {

        this.clientService.saveClient(id, dto);
    }

    @RequestMapping(path = { "/{id}" }, method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        this.clientService.deleteClient(id);
    }

}
