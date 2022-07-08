package com.capgemini.ccsw.tutorial.game;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.tutorial.author.AuthorService;
import com.capgemini.ccsw.tutorial.category.CategoryService;
import com.capgemini.ccsw.tutorial.game.model.Game;
import com.capgemini.ccsw.tutorial.game.model.GameDto;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<Game> find(String title, Long idCategory) {
        return this.gameRepository.find(title, idCategory);
    }

    @Override
    public void save(Long id, GameDto dto) {
        // TODO Auto-generated method stub
        Game game = null;

        if (id != null)
            game = this.gameRepository.findById(id).orElse(null);
        else
            game = new Game();

        /*
         * En BeanUtils.copyProperties() le indicamos los atributos que no queremos que
         * copie.
         * 
         * eso se utiliza para que copie los atributos simples, id, fechas, nombres, y
         * NO copie los objetos, porque esos los seteais desde fuera, setGame,
         * setClient, setAuthor
         */
        // De esta manera no se sobreescribirá el id, el Author y la Category
        BeanUtils.copyProperties(dto, game, "id", "author", "category");

        game.setAuthor(authorService.get(dto.getAuthor().getId()));
        game.setCategory(categoryService.get(dto.getCategory().getId()));

        this.gameRepository.save(game);
    }

    @Override
    public List<Game> findByTitle(String title) {
        // TODO Auto-generated method stub
        return (List<Game>) this.gameRepository.findByTitle(title);
    }

    @Override
    public Game getGameById(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

}
