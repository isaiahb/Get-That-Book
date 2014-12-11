package com.wicgames.gameObjects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.game.LevelScene;
import com.wicgames.mobs.Character;
import com.wicgames.physics.Body;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.window.Scene;

public class LevelEnd extends GameObject{
	public LevelEnd(int x,int y){
		body = new Rectangle(x,y,64,64){
			@Override
			public void hit(Body body) {
				super.hit(body);
				if(Character.hitbox != null)
					if(body.getClass().equals(Character.hitbox))((LevelScene)Scene.currentScene).finishLevel();
			}
		};
		try {
			texture = ImageIO.read(new File("bin/assets/textures/EndDoor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		body.setMaterial(Material.Static);
		created(this);
	}
}
