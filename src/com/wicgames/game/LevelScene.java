package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.wicgames.gameObjects.GameObject;
import com.wicgames.gameObjects.InvisiblePlatform;
import com.wicgames.gameObjects.Platform;
import com.wicgames.gameObjects.Scenery;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.window.Scene;
import com.wicgames.mobs.Character;
public class LevelScene extends Scene {
	public String levelName;
	public Data textureData;
	public SpriteSheet textures;
	private Character player;
	public void init() {
		loadLevel("bin/assets/data/levels/" + levelName);
		player = new Character();
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
		try {
			int x = 0,y = 0; //X,Y of tile
			BufferedReader levelReader = new BufferedReader(new FileReader(path));
			textureData = new Data(path + "text");
			textures = new SpriteSheet(textureData.getValue("spritesheet"), Integer.parseInt(textureData.getValue("x")), Integer.parseInt(textureData.getValue("y")), Integer.parseInt(textureData.getValue("border")));
			while(levelReader.ready()){
				x = 0; //New Row set x to 0
				char[] levelLine = levelReader.readLine().toCharArray();
				for(char tile : levelLine) {
					
					if(tile == '&')break; //& is character for end of level background
					if(String.valueOf(tile).equals(textureData.getValue("sky"))){x++; continue;}
					GameObject obj;
					if (!String.valueOf(tile).equals(textureData.getValue("invisible"))) 
							obj = tile < 64? 
									new Platform(x,y,1,1,textures.getImage(Integer.parseInt(textureData.getValue(String.valueOf(tile))))):					
									new Scenery(x,y,1,1,textures.getImage(Integer.parseInt(textureData.getValue(String.valueOf(tile)))));
					else 
						obj = new InvisiblePlatform(x,y,1,1);			
					obj.body.anchor();
					x++;
				}
				y++;
			}
			while(levelReader.ready()){
				
			}
			levelReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
