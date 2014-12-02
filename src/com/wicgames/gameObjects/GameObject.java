package com.wicgames.gameObjects;

import java.awt.Graphics;

import com.wicgames.physics.Body;

public abstract class GameObject {
	Body body;
	public abstract void draw(Graphics g);
}
