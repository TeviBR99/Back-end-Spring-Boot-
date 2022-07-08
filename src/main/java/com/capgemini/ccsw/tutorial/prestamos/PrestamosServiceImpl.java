package com.capgemini.ccsw.tutorial.prestamos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.client.ClientService;
import com.capgemini.ccsw.tutorial.client.model.Client;
import com.capgemini.ccsw.tutorial.game.GameService;
import com.capgemini.ccsw.tutorial.game.model.Game;
import com.capgemini.ccsw.tutorial.prestamos.model.Prestamos;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosDto;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosSearchDto;

@Service
@Transactional
public class PrestamosServiceImpl implements PrestamosService {

    @Autowired
    PrestamosRepository prestamosRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    @Override
    public Page<Prestamos> findPage(PrestamosSearchDto dto, String title, Date date, String name) {
        // TODO Auto-generated method stub
        Page<Prestamos> prestamosPage = null;

        prestamosPage = this.prestamosRepository.findByGameOrDateOrClient(title, date, name, dto.getPageable());

        return prestamosPage;
    }

    @Override
    public void save(Long id, PrestamosDto dto) {
        Prestamos prestamo = null;

        if (id != null) {
            prestamo = this.prestamosRepository.findById(id).orElse(null);
        } else {
            prestamo = new Prestamos();
        }
        // BeanUtils.copyProperties(dto,prestamo,"id","client","game")
        prestamo.setClient(this.clientService.getClientById(dto.getClient().getId()));
        prestamo.setGame(this.gameService.getGameById(dto.getGame().getId()));
        prestamo.setDayIn(dto.getDayIn());
        prestamo.setDayOut(dto.getDayOut());

        this.prestamosRepository.save(prestamo);

    }

    @Override
    public void delete(Long id) {
        this.prestamosRepository.deleteById(id);

    }

    @Override
    public List<Prestamos> checkGames(Long idGame, Date dayOut, Date dayIn) {

        List<Prestamos> prestamos = new ArrayList<Prestamos>();

        Game game = null;
        prestamos = null;

        game = this.gameService.getGameById(idGame);

        if (game != null) {
            prestamos = this.prestamosRepository.findIfExistsPrestamos(game.getId(), dayIn, dayOut);
        }

        return prestamos;
    }

    @Override
    public List<Prestamos> checkClient(Long idClient, Date dayOut, Date dayIn) {
        List<Prestamos> prestamos = new ArrayList<Prestamos>();

        prestamos = null;
        Client client = this.clientService.getClientById(idClient);
        if (client != null) {
            prestamos = this.prestamosRepository.findIfExistsGameAsociated(client.getId(), dayIn, dayOut);

        }

        return prestamos;
    }

}
