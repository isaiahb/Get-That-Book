package com.wicgames.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import com.wicgames.game.Main;
import com.wicgames.physics.Body;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.window.Scene;

public abstract class GameObject implements Drawable {
	public static int tileSize = Main.TILESIZE;	//Creates a global variable in all game objects with the tile size
	public Body body;		//Holds the body, which handles the physics
	public Image texture;	//Holds the texture used to draw the game object
	public static void created(GameObject object) {
		//Called when ever a game object is created, anything that extends GameObject needs to call this in its constructor
		Scene.currentScene.objects.add(object);
		Scene.currentScene.bodies.add(object.body);
		Scene.currentScene.drawables.add(object);
	}
	
	public void draw(Graphics2D graphics2D) {
		//TO DO - finish this method
		//Will be used to draw the game object
		if (texture != null)
			graphics2D.drawImage(texture,(int)body.position.x,(int)body.position.y,null);
		else{
			graphics2D.setColor(Color.PINK);
	        graphics2D.fillRect((int)body.position.x,(int)body.position.y,(int)body.size.x, (int) body.size.y);
	        graphics2D.setColor(Color.BLACK);
	        graphics2D.drawRect((int)body.position.x,(int)body.position.y,(int)body.size.x, (int) body.size.y);
		}
	}
	@Override
	public void updateImage(Image texture) {
		this.texture = texture;
	}
}
