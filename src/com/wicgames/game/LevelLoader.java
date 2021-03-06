package com.wicgames.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.wicgames.gameObjects.GameObject;
import com.wicgames.gameObjects.LevelEnd;
import com.wicgames.gameObjects.PushPlatform;
import com.wicgames.gameObjects.Scenery;
import com.wicgames.mobs.BasicTribeMember;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class LevelLoader {
	/**
	 * Loads level from file at path
	 * @param path Location of level file
	 */
	public static void loadLevel(String path,Vector2 spawn){
		Scene currentLevel = Scene.currentScene;
		int levelNumber = Integer.parseInt(currentLevel.name.substring(5, currentLevel.name.length()));
		try {
			int x = 0,y = 0; //X,Y of tile
			BufferedReader levelReader = new BufferedReader(new FileReader(path));
			Data textureData = new Data(path + "text");
			System.out.println(path + "text");

			currentLevel.textures = new SpriteSheet(textureData.getValue("spritesheet"), Integer.parseInt(textureData.getValue("x")), Integer.parseInt(textureData.getValue("y")), Integer.parseInt(textureData.getValue("border")),0);
			String spawnx = (textureData.getValue("spawnx"));
			String spawny = (textureData.getValue("spawny"));
			spawn.setTo(Integer.parseInt(spawnx), Integer.parseInt(spawny));
			((LevelScene)Scene.currentScene).solid = new boolean[Integer.parseInt(textureData.getValue("sizex"))][Integer.parseInt(textureData.getValue("sizey"))];
			boolean[][] solid = ((LevelScene)Scene.currentScene).solid;
			LevelReader:
			while(levelReader.ready()){
				x = 0; //New Row set x to 0
				char[] levelLine = levelReader.readLine().toCharArray();
				for(char tile : levelLine) {
					
					if(tile == '&')break LevelReader; //& is character for end of level background
					if(String.valueOf(tile).equals(textureData.getValue("sky"))){x++; continue;}
					GameObject obj;		
					obj = new Scenery(x,y,1,1, currentLevel.textures.getImage(Integer.parseInt(textureData.getValue(String.valueOf(tile)))));
					obj.body.setMaterial(Material.Static);
					solid[x][y] = true;
					currentLevel.size.x = Math.max(x * GameObject.tileSize, currentLevel.size.x);
					currentLevel.size.y = Math.max(y * GameObject.tileSize, currentLevel.size.y);
					x++;
				}
				y++;
			}
			while(levelReader.ready()){
				String[] objectInfo = levelReader.readLine().split(" ");
				switch(Integer.parseInt(objectInfo[0])){
				case 0: //Level End
					new LevelEnd(Integer.parseInt(objectInfo[1]),Integer.parseInt(objectInfo[2]));
					break;
				case 1: //Wierd Isaiah Tiles
					new PushPlatform(Integer.parseInt(objectInfo[1]), Integer.parseInt(objectInfo[2]), Integer.parseInt(objectInfo[3]), Integer.parseInt(objectInfo[4]), currentLevel.textures.getImage(Integer.parseInt(objectInfo[5])));
					break;
				case 2: //Light
					Light.lightSources.add(new LightSource(Integer.parseInt(objectInfo[1]), Integer.parseInt(objectInfo[2]), Short.parseShort(objectInfo[5])));
					break;
				}
			}
			levelReader.close();
			BufferedReader hitboxReader = new BufferedReader(new FileReader(path + "hitmap"));
			while(hitboxReader.ready()){
				String[] rectangle = hitboxReader.readLine().split(" ");
				Rectangle hitbox = new Rectangle(Integer.parseInt(rectangle[0]) * GameObject.tileSize,Integer.parseInt(rectangle[1]) * GameObject.tileSize,Integer.parseInt(rectangle[2]) * GameObject.tileSize,Integer.parseInt(rectangle[3]) * GameObject.tileSize);
				currentLevel.bodies.add(hitbox);
				hitbox.setMaterial(Material.Static);
			}
			hitboxReader.close();
			BufferedReader darknessReader = new BufferedReader(new FileReader(path + "darkmap"));
			while(darknessReader.ready()){
				String[] rectangle = darknessReader.readLine().split(" ");
				java.awt.Rectangle darkness = new java.awt.Rectangle(Integer.parseInt(rectangle[0]) * GameObject.tileSize,Integer.parseInt(rectangle[1]) * GameObject.tileSize,Integer.parseInt(rectangle[2]) * GameObject.tileSize,Integer.parseInt(rectangle[3]) * GameObject.tileSize);
			}
			darknessReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new BasicTribeMember(50,100);
		Light.refresh();
	}

}
