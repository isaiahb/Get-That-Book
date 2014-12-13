package com.wicgames.gameObjects;

import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.Vector2;

public class Rock extends Projectile{

	public Rock(Vector2 direction, double speed,Vector2 position) {
		super(direction, speed);
		body = new Rectangle((int)position.x,(int)position.y,16,16);
		created(this);
		super.init();
	}

}
