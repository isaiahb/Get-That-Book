package com.wicgames.gameObjects;

import com.wicgames.game.LevelScene;
import com.wicgames.mobs.Character;
import com.wicgames.physics.Body;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.window.Scene;

public class LevelEnd extends GameObject{
	public LevelEnd(int x,int y){
		body = new Rectangle(x * tileSize,y * tileSize,64,64){
			@Override
			public void hit(Body body) {
				super.hit(body);
				if(Character.hitbox != null)
					if(body.getClass().equals(Character.hitbox))((LevelScene)Scene.currentScene).finishLevel();
			}
		};
		body.setMaterial(Material.Static);
		created(this);
	}
}
