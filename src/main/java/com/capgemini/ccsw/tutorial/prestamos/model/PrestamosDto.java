package com.capgemini.ccsw.tutorial.prestamos.model;

import java.sql.Date;

import com.capgemini.ccsw.tutorial.client.model.ClientDto;
import com.capgemini.ccsw.tutorial.game.model.GameDto;

/*
 * 
 * export class Prestamos{
    id: number;
    game: Game;
    client: Client;
    dayIn: Date;
    dayOut: Date;
}
 */
public class PrestamosDto {
    private Long id;
    private GameDto game;
    private ClientDto client;
    private Date dayIn;
    private Date dayOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public Date getDayIn() {
        return dayIn;
    }

    public void setDayIn(Date dayIn) {
        this.dayIn = dayIn;
    }

    public Date getDayOut() {
        return dayOut;
    }

    public void setDayOut(Date dayOut) {
        this.dayOut = dayOut;
    }
}
