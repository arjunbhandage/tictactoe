
package com.oss.nokia.neo.restservice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oss.nokia.neo.exceptions.GameDoesNotExist;
import com.oss.nokia.neo.exceptions.GameOverException;
import com.oss.nokia.neo.exceptions.InvalidCharacterException;

@RestController
public class GameController {

    private AtomicLong uniqueId = new AtomicLong();
    Map<Long, Game> gameMap = new HashMap<>();

    @PostMapping(path = "game/{id}/move")
    public String move(@PathVariable Long id, @RequestBody Move move) {
        Game game = gameMap.get(id);
        if (game == null) throw new GameDoesNotExist();
        if (game.flag) {
            throw new GameOverException();
        }
        game.makeMove(move);
        game.computerMove();
        return game.board();
    }

    @PostMapping(path = "/game")
    public String newGame(@RequestBody Game game) {
        Long uid = uniqueId.getAndIncrement();
        if (game.getCharacter().equals('o')) {
            game.setComputerCharacter('x');
            game.computerMove();
        } else if (!game.getCharacter().equals('x')) {
            throw new InvalidCharacterException();
        }
        gameMap.put(uid, game);
        return "Player Name: " + game.getName() + "\n" + "Player Character: " + game.getCharacter() + "\n"
                + "Game UID: " + uid;
    }

    @GetMapping("game/{id}")
    public String game(@PathVariable Long id) {
        Game game = gameMap.get(id);
        if (game == null) throw new GameDoesNotExist();
        return game.board();
    }
}
