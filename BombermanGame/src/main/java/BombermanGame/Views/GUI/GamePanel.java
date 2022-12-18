package BombermanGame.Views.GUI;

import BombermanGame.Views.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(GameWindowsFrame gameWindowsFrame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE_MULTIPLE, Game.HEIGHT * Game.SCALE_MULTIPLE));

        game = new Game(gameWindowsFrame);
        add(game);
        game.setVisible(true);

        setVisible(true);
        setFocusable(true);
    }

    public void changeSize() {
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE_MULTIPLE, Game.HEIGHT * Game.SCALE_MULTIPLE));
        revalidate();
        repaint();
    }

    public Game getGame() {
        return game;
    }
}