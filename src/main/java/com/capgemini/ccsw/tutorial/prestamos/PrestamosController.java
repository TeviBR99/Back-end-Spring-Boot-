package com.capgemini.ccsw.tutorial.prestamos;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.tutorial.config.mapper.BeanMapper;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosDto;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosSearchDto;

@RequestMapping(value = "/prestamos")
@RestController
@CrossOrigin(origins = "*")
public class PrestamosController {

    @Autowired
    PrestamosService prestamosService;

    @Autowired
    BeanMapper beanMapper;

    /*
     * Este método devuelve el conjunto de Prestamos páginados, es decir, nos
     * devuelve una página con un número determinados de elementos indicados en el
     * dto que se le pasa (de tipo PrestamosSearchDto). Usamos el verbo POST porque
     * es mucha información para poner en la cabecera.
     */

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamosDto> displayPage(@RequestBody PrestamosSearchDto dto,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "date", required = false) Date date,
            @RequestParam(value = "name", required = false) String name) {

        return this.beanMapper.mapPage(prestamosService.findPage(dto, title, date, name), PrestamosDto.class);
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void savePrestamo(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamosDto dto) {
        this.prestamosService.save(id, dto);
    }

    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.DELETE)
    public void deletePrestamo(@PathVariable("id") Long id) {
        this.prestamosService.delete(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PrestamosDto> checkGames(@RequestParam(value = "idGame", required = true) Long idGame,
            @RequestParam(value = "dayOut", required = true) Date dayOut,
            @RequestParam(value = "dayIn", required = true) Date dayIn) {

        return this.beanMapper.mapList(prestamosService.checkGames(idGame, dayOut, dayIn), PrestamosDto.class);

    }

    @RequestMapping(path = "/client", method = RequestMethod.GET)
    public List<PrestamosDto> checkClient(@RequestParam(value = "idClient", required = true) Long idClient,
            @RequestParam(value = "dayOut", required = true) Date dayOut,
            @RequestParam(value = "dayIn", required = true) Date dayIn) {

        return this.beanMapper.mapList(prestamosService.checkClient(idClient, dayOut, dayIn), PrestamosDto.class);

    }

}
