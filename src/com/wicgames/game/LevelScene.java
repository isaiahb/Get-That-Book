package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wicgames.mobs.Character;
import com.wicgames.physics.Force;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;
public class LevelScene extends Scene {
	public Character player;
	public Force gravity = new Force.Gravity(0, 550);
	public Vector2 spawn = new Vector2();
	
	public void init() {
		LevelLoader.loadLevel("bin/assets/data/levels/" + name, spawn);
		player = new Character();
		gravity.add(player.body);
		forces.add(gravity);
		player.respawn(spawn.x, spawn.y);
	}
	public LevelScene(int level){
		name = "level" + level; //Name of level file
	}
	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(110, 200, 235));
		graphics2D.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		super.draw(graphics2D);
	}

	public void update(double delta) {
		super.update(delta);
		
	}
	
}
