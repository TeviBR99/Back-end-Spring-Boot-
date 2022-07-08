package com.capgemini.ccsw.tutorial.client;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.tutorial.client.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findClientByName(String name);
}
