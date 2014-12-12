package com.wicgames.gameObjects;

public abstract class Projectile extends GameObject{
	public Projectile(boolean gravity){
		body.affectedGravity = gravity;
	}
}
