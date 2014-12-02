package com.wicgames.wicLibrary;

import java.awt.Graphics2D;
import java.awt.Image;

public class Button {
	public Vector2 position = new Vector2();
	public Vector2 size = new Vector2();
	public Button (int x,int y, int width,int height) {
		position.setTo(x,y);
		size.setTo(width, height);
	}
	public void setTexture(String texture) {
		//this.texture = new ImageIO.read(new File)
	}
	public void draw(Graphics2D g) {}
}
