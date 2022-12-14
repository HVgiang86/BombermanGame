package bomberman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import bomberman.Games;
import bomberman.Exceptions.LoadLevelException;

public class GamePanel extends JPanel {
	private final Games game;

	public GamePanel(Frame frame) throws LoadLevelException {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Games.width * Games.scale, Games.height * Games.scale));
		game = new Games(frame);
		add(game);
		game.setVisible(true);

		setVisible(true);
		setFocusable(true);

	}

	public void changeSize() {
		setPreferredSize(new Dimension(Games.width * Games.scale, Games.height * Games.scale));
		revalidate();
		repaint();
	}

	public Games getGame() {
		return game;
	}
}
