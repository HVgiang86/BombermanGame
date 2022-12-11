package bomberman.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bomberman.Games;
import bomberman.Exceptions.LoadLevelException;
import bomberman.GUI.Menu.Menu;

public class Frame extends JFrame {
	public GamePanel gamePanel;
	private final InfoPanel infoPanel;

	private final Games game;

	public Frame() throws LoadLevelException {
		setJMenuBar(new Menu(this));

		JPanel containerPanel = new JPanel(new BorderLayout());
		gamePanel = new GamePanel(this);
		infoPanel = new InfoPanel(gamePanel.getGame());

		containerPanel.add(infoPanel, BorderLayout.PAGE_START);
		containerPanel.add(gamePanel, BorderLayout.PAGE_END);

		game = gamePanel.getGame();

		add(containerPanel);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		game.start();
	}


	public void changeLevel(int i) {
		try {
			game.getBoard().loadLevel(i);
		} catch (LoadLevelException e) {
			e.printStackTrace();
		}
	}

	public void newGame() {
		try {
			game.getBoard().newGame();
		} catch (LoadLevelException e) {
			e.printStackTrace();
		}
	}

	public void restart() {
		try {
			game.getBoard().restart();
		} catch (LoadLevelException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		game.getBoard().gamePause();
	}

	public void resume() {
		game.getBoard().gameResume();
	}

	public void setTime(int time) {
		infoPanel.setTime(time);
	}

	public void setLives(int lives) {
		infoPanel.setLives(lives);
	}

	public void setPoints(int points) {
		infoPanel.setPoints(points);
	}

	public void auto() {
		game.getBoard().getBomber().setAuto(false);;
	}

	public void manual() {
		game.getBoard().getBomber().setAuto(false);
	}
}
