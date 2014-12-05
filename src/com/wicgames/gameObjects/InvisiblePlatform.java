package com.wicgames.gameObjects;

import java.awt.Graphics2D;

public class InvisiblePlatform extends Platform{

	public InvisiblePlatform(int x, int y, int width, int height) {
		super(x, y, width, height, null);
	}
	public void draw(Graphics2D g){}
}
