package bomberman.GUI.Menu;

import javax.swing.JMenuBar;

import bomberman.Games;
import bomberman.Exceptions.LoadLevelException;
import bomberman.GUI.Frame;

public class Menu extends JMenuBar{
	 public Menu(Frame frame) throws LoadLevelException {
	        add(new Games(frame));
	        add(new Player(frame));
	        add(new Level(frame));
	        add(new Options(frame));
	    }
}
