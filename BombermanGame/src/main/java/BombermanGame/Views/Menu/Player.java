package BombermanGame.Views.Menu;

import BombermanGame.Views.GUI.GameWindowsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JMenu {
    GameWindowsFrame gameWindowsFrame;

    public Player(GameWindowsFrame gameWindowsFrame) {
        super("Player");
        this.gameWindowsFrame = gameWindowsFrame;

        JMenuItem auto = new JMenuItem("Auto");
        auto.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(auto);

        JMenuItem manual = new JMenuItem("Manual");
        manual.addActionListener(new MenuActionListener(gameWindowsFrame));
        add(manual);
    }

    static class MenuActionListener implements ActionListener {
        public GameWindowsFrame gameWindowsFrame;

        public MenuActionListener(GameWindowsFrame gameWindowsFrame) {
            this.gameWindowsFrame = gameWindowsFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Auto")) gameWindowsFrame.auto();
            else if (e.getActionCommand().equals("Manual")) gameWindowsFrame.manual();
        }
    }
}