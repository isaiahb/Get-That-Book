package com.wicgames.gameObjects;

import com.wicgames.wicLibrary.Vector2;

public abstract class Projectile extends GameObject{
	Vector2 direction;
	double speed;
	public Projectile(Vector2 direction, double speed){
		this.direction = direction;
		this.speed = speed;
	}
	public void init(){
		body.affectedGravity = false;
		direction.normalize();
		body.velocity.setTo(Vector2.mul(direction,speed));
	}
}
