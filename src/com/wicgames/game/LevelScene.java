package com.wicgames.game;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.wicgames.gameObjects.GameObject;
import com.wicgames.gameObjects.Platform;
import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.SpriteSheet;

public class LevelScene extends Scene {
	public String levelName;
	public Data textureData;
	public SpriteSheet textures;
	public void init() {
		loadLevel("bin/assets/data/levels/" + levelName);
	}
	public LevelScene(int level){
		levelName = "level" + level; //Name of level file
	}
	@Override
	public void draw(Graphics2D graphics2D) {
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
				for(char tile : levelLine){
					GameObject obj = null;
					obj = tile == '0' ? new Platform(x,y,1,1) : obj;
					if(tile == '&')break; //& is character for end of level background
					x++;
				}
				y++;
			}
			while(levelReader.ready()){
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
