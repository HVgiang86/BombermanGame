package BombermanGame.Views.Menu;

import BombermanGame.Views.GUI.GameWindowsFrame;

import javax.swing.*;

public class Menu extends JMenuBar {
    public Menu(GameWindowsFrame gameWindowsFrame) {
        add(new GameMenu(gameWindowsFrame));
        add(new Player(gameWindowsFrame));
        add(new Level(gameWindowsFrame));
        add(new Options(gameWindowsFrame));
    }
}