package Controller;

import model.Game;

import java.io.IOException;

public class Controller {
    Game game = new Game();

    public Controller() throws IOException {
    }

    public void playTheGame() throws IOException {
        game.start();
        while (game.restart) {
            game.settings();
            game.cycle();
            game.end();
        }
    }


}
