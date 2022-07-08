package com.capgemini.ccsw.tutorial.prestamos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.ccsw.tutorial.prestamos.model.Prestamos;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosDto;
import com.capgemini.ccsw.tutorial.prestamos.model.PrestamosSearchDto;

public interface PrestamosService {

    public Page<Prestamos> findPage(PrestamosSearchDto dto, String title, Date date, String name);

    public void save(Long id, PrestamosDto dto);

    public void delete(Long id);

    public List<Prestamos> checkGames(Long idGame, Date dayOut, Date dayIn);

    public List<Prestamos> checkClient(Long idClient, Date dayOut, Date dayIn);
}
