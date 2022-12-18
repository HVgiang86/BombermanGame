package BombermanGame.Views.Menu;

import BombermanGame.Views.GUI.GameWindowsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameMenu extends JMenu {
    public GameWindowsFrame gameWindowsFrame;

    public GameMenu(GameWindowsFrame gameWindowsFrame) {
        super("Game");
        this.gameWindowsFrame = gameWindowsFrame;

        JMenuItem newGame = new JMenuItem("New");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newGame.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(newGame);

        JMenuItem restart = new JMenuItem("Restart");
        restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.SHIFT_DOWN_MASK));
        restart.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(restart);

        JMenuItem pause = new JMenuItem("Pause");
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        pause.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(pause);

        JMenuItem resume = new JMenuItem("Resume");
        resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        resume.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(resume);
    }

    static class MenuActionListener implements ActionListener {
        public GameWindowsFrame gameWindowsFrame;

        public MenuActionListener(GameWindowsFrame gameWindowsFrame) {
            this.gameWindowsFrame = gameWindowsFrame;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("New")) gameWindowsFrame.newGame();
            else if (event.getActionCommand().equals("Restart")) gameWindowsFrame.restart();
            else if (event.getActionCommand().equals("Pause")) gameWindowsFrame.pause();
            else if (event.getActionCommand().equals("Resume")) gameWindowsFrame.resume();
        }
    }
}