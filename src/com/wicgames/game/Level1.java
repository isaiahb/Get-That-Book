package com.wicgames.game;

import java.awt.Graphics2D;

import com.wicgames.scene.Scene;

public class Level1 extends Scene {

	public void init() {
		System.out.println("Level One Began");
	}

	
	@Override
	public void draw(Graphics2D graphics2D) {
		super.draw(graphics2D);
		graphics2D.fillRect(5, 5, 1, 1);
	}

	public void update(double delta) {
		super.update(delta);
		
	}

	
}
