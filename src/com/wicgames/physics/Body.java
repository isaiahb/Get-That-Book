package com.wicgames.physics;

import com.wicgames.wicLibrary.Vector2;


public class Body {
	public Vector2 size = new Vector2();
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Vector2 acceleration = new Vector2();
	
	//Constructors
	public Body (int x, int y, int width, int height) {
		position.setTo(x,y);
		size.setTo(width, height);
	}
	public Body (Vector2 position, Vector2 size) {
		this.position.setTo(position);
		this.size.setTo(size);
	}
	
	//Update Method
	public void update(double delta) {
		velocity.add(acceleration, delta);
		position.add(velocity, delta);
		
	}
}
