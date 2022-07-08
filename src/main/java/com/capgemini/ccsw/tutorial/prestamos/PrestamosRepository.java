package com.capgemini.ccsw.tutorial.prestamos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.tutorial.prestamos.model.Prestamos;

public interface PrestamosRepository extends CrudRepository<Prestamos, Long> {

    Page<Prestamos> findAll(Pageable pageable);

    /*
     * @Query("select p from Prestamos p" +
     * " where (:title is null or p.game.title like '%'||:title||'%' ) " +
     * "and ( :date is null or  to_date(:date,'YYYY/MM/DD') between to_date(p.dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') )"
     * + "and (:name is null or p.client.name like '%'||:name||'%')")
     */
    /*
     * 
     * value = "SELECT * FROM USERS WHERE LASTNAME = ?1", countQuery =
     * "SELECT count(*) FROM USERS WHERE LASTNAME = ?1", nativeQuery = true
     */

    @Query("select p from Prestamos p" + " where (:title is null or p.game.title like '%'||:title||'%' ) "
            + "and ( :date is null or  to_date(:date,'YYYY/MM/DD') between to_date(p.dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') )"
            + "and (:name is null or p.client.name like '%'||:name||'%')")
    Page<Prestamos> findByGameOrDateOrClient(String title, Date date, String name, Pageable pageable);

    /*
     * 
     * 
     * select * from prestamos p join game g on p.game_id = g.id where p.game_id='1'
     * and (( p.day_in >'2022/12/30' and p.day_in < '2022/12/31') or ( p.day_in <=
     * '2022/12/30' and p.day_out >= '2022/12/31' ) or( p.day_out > '2022/12/30' and
     * p.day_out < '2022/12/31'));
     */

    @Query("select p from Prestamos p " + "where p.game.id=:idGame "
            + "and ( (to_date(p.dayIn,'YYYY/MM/DD') > to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayIn,'YYYY/MM/DD') < to_date(:dayOut,'YYYY/MM/DD')) "
            + "    or ( to_date(p.dayIn,'YYYY/MM/DD') <= to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') >= to_date(:dayOut,'YYYY/MM/DD') ) "
            + "    or( to_date(p.dayOut,'YYYY/MM/DD') > to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') < to_date(:dayOut,'YYYY/MM/DD'))"
            + ")")
    List<Prestamos> findIfExistsPrestamos(@Param("idGame") Long idGame, @Param("dayIn") Date dayIn,
            @Param("dayOut") Date dayOut);

    @Query("select p from Prestamos p " + "where p.client.id=:idClient "
            + "and ( (to_date(p.dayIn,'YYYY/MM/DD') > to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayIn,'YYYY/MM/DD') < to_date(:dayOut,'YYYY/MM/DD')) "
            + "    or ( to_date(p.dayIn,'YYYY/MM/DD') <= to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') >= to_date(:dayOut,'YYYY/MM/DD') ) "
            + "    or( to_date(p.dayOut,'YYYY/MM/DD') > to_date(:dayIn,'YYYY/MM/DD') and to_date(p.dayOut,'YYYY/MM/DD') < to_date(:dayOut,'YYYY/MM/DD'))"
            + ") ")
    List<Prestamos> findIfExistsGameAsociated(@Param("idClient") Long idClient, @Param("dayIn") Date dayIn,
            @Param("dayOut") Date dayOut);

}
