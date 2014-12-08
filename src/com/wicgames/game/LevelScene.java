package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.wicgames.gameObjects.GameObject;
import com.wicgames.gameObjects.InvisiblePlatform;
import com.wicgames.gameObjects.Platform;
import com.wicgames.gameObjects.Scenery;
import com.wicgames.mobs.Character;
import com.wicgames.physics.Force;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.window.Scene;
public class LevelScene extends Scene {
	public String levelName;
	public Data textureData;
	public SpriteSheet textures;
	private Character player;
	private Force gravity = new Force.Gravity(0, 550);
	public void init() {
		loadLevel("bin/assets/data/levels/" + levelName);
		player = new Character();
		gravity.add(player.body);
		forces.add(gravity);
	}
	public LevelScene(int level){
		levelName = "level" + level; //Name of level file
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
	/**
	 * Loads level from file at path
	 * @param path Location of level file
	 */
	public void loadLevel(String path){
		int levelNumber = Integer.parseInt(levelName.substring(5, levelName.length()));
		System.out.println(levelNumber);
		try {
			int x = 0,y = 0; //X,Y of tile
			BufferedReader levelReader = new BufferedReader(new FileReader(path));
			textureData = new Data(path + "text");
			textures = new SpriteSheet(textureData.getValue("spritesheet"), Integer.parseInt(textureData.getValue("x")), Integer.parseInt(textureData.getValue("y")), Integer.parseInt(textureData.getValue("border")),0);
			LevelReader:
			while(levelReader.ready()){
				x = 0; //New Row set x to 0
				char[] levelLine = levelReader.readLine().toCharArray();
				for(char tile : levelLine) {
					
					if(tile == '&')break LevelReader; //& is character for end of level background
					if(String.valueOf(tile).equals(textureData.getValue("sky"))){x++; continue;}
					GameObject obj;		
					obj = new Scenery(x,y,1,1,textures.getImage(Integer.parseInt(textureData.getValue(String.valueOf(tile)))));
					obj.body.setMaterial(Material.Static);
					size.x = Math.max(x * GameObject.tileSize, size.x);
					size.y = Math.max(y * GameObject.tileSize, size.y);
					x++;
				}
				y++;
			}
			while(levelReader.ready()){
				
			}
			levelReader.close();
			BufferedReader hitboxReader = new BufferedReader(new FileReader(path + "hitmap"));//TODO wont work above level ten
			while(hitboxReader.ready()){
				String[] rectangle = hitboxReader.readLine().split(" ");
				Rectangle hitbox = new Rectangle(Integer.parseInt(rectangle[0]) * GameObject.tileSize,Integer.parseInt(rectangle[1]) * GameObject.tileSize,Integer.parseInt(rectangle[2]) * GameObject.tileSize,Integer.parseInt(rectangle[3]) * GameObject.tileSize);
				Scene.currentScene.bodies.add(hitbox);
				hitbox.setMaterial(Material.Static);
				
			}
			hitboxReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
