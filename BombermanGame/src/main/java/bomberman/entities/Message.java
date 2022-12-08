package bomberman.entities;

import java.awt.Color;

import bomberman.Graphics.Screen;


public class Message extends Entity {
	
	protected String message;
	protected int duration;
	protected Color color;
	protected int size;

	
	//hien thi thong bao khi giet duoc quai
	public Message(String message, double x, double y,  int duration,Color color, int size) {
		this.x = x;
		this.y = y;
		this.message = message;
		this.duration = duration*60;
		this.color = color;
		this.size = size;
	}
	
	public String getMessage() {
		return message;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSize() {
		return size;
	}


	public Color getColor() {
		return color;
	}


	@Override
	public void update() {
		
	}

	@Override
	public boolean collide(Entity e) {
			return true;
	}

	@Override
	public void render(Screen screen) {
	
		
	}

}
