package bomberman.GUI.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import bomberman.GUI.Frame;

public class Player extends JMenu {
	  Frame frame;

	    public Player(Frame frame) {
	        super("Player");
	        this.frame = frame;

	        JMenuItem auto = new JMenuItem("Auto");
	        auto.addActionListener(new MenuActionListener(frame));
	        add(auto);

	        JMenuItem manual = new JMenuItem("Manual");
	        manual.addActionListener(new MenuActionListener(frame));
	        add(manual);
	    }

	    static class MenuActionListener implements ActionListener {
	        public Frame frame;

	        public MenuActionListener(Frame frame) {
	            this.frame = frame;
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (e.getActionCommand().equals("Auto")) frame.auto();
	            else if (e.getActionCommand().equals("Manual")) frame.manual();
	        }
	    }
}
