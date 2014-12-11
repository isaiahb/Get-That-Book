package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.wicgames.gameObjects.GameObject;
import com.wicgames.physics.Body;
import com.wicgames.physics.CollisionDetection;
import com.wicgames.physics.CollisionResolution;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Light {
	public static short[][] lightMap;
	public static short[][] totalLightMap;
	private static Image lightFilter;
	public static void generateLightMap(int x,int y){
		lightMap = new short[x][y];
		totalLightMap = new short[x][y];
		for (int i = 0; i < lightMap.length; i++) {
			for (int j = 0; j < lightMap[i].length; j++) {
				lightMap[i][j] = 0;
				totalLightMap[i][j] = 0;
			}
		}
		calculateSunlight();
		lightFilter = generateLightFilter();
	}
	public static void calculateSunlight(){
		for(int x = 0;x < totalLightMap.length;x++){
			Body hit = collidedHitbox(x,0,1,totalLightMap[x].length);
			if(hit != null){
				for(int y = 0;y < hit.position.y + hit.size.y;y++)
					if(!(y >= totalLightMap[x].length - 1))
					totalLightMap[x][y] = 255;
				calculateLight(x,(int)hit.position.y,(short)255);
			}else{
				for(int y = 0;y < totalLightMap[0].length;y++)
					totalLightMap[x][y] = 255;
			}
		}
	}
	public static Body collidedHitbox(int x,int y,int width,int height){
		Body c = null;
		for(GameObject b : Scene.currentScene.objects){
			if(b.body.position.x > x + width || b.body.position.x + b.body.size.x < x);
			else if(b.body.position.y > y + height || b.body.position.y + b.body.size.y < y);
			else if(c == null || b.body.position.y < c.position.y)c = b.body;
		}
		return c;
	}
	public static void calculateLight(int x,int y,short lightLevel){
		boolean[][] done = new boolean[lightMap.length][lightMap[0].length];
		recursiveRay(x,y,lightLevel,1,0,done);
		for(int j = 10;j < 500;j++)
			for(int i = 10;i < 500;i++){
				recursiveRay(x,y,lightLevel,j,i,done);
				recursiveRay(x,y,lightLevel,-i,j,done);
				recursiveRay(x,y,lightLevel,j,-i,done);
				recursiveRay(x,y,lightLevel,-i,-j,done);
			}
		for(int i = 0;i < 10;i++){
			recursiveRay(x,y - i,lightLevel,1,0,done);
			recursiveRay(x - i,y,lightLevel,0,1,done);
			recursiveRay(x,y + i,lightLevel,1,0,done);
			recursiveRay(x + i,y,lightLevel,0,1,done);
			recursiveRay(x,y - i,lightLevel,-1,0,done);
			recursiveRay(x - i,y,lightLevel,0,-1,done);
			recursiveRay(x,y + i,lightLevel,-1,0,done);
			recursiveRay(x + i,y,lightLevel,0,-1,done);
		}
		for (int i = 0; i < lightMap.length; i++) {
			for (int j = 0; j < lightMap[i].length; j++) {
				if((int)totalLightMap[i][j] + (int)lightMap[i][j] <= 255)
					totalLightMap[i][j] += lightMap[i][j];
				else
					totalLightMap[i][j] = 255;

			}
		}
	}
	public static void recursiveRay(int x,int y,short lightLevel,int xDirection,int yDirection,boolean[][] done){
		if(lightLevel <= 0 || x < 0 || x > lightMap.length - 1 || y < 0 || y > lightMap[0].length - 1)return;
		if(!done[x][y]){
			lightMap[x][y] = lightLevel;
		}
		done[x][y] = true;
		if(lightLevel - (Math.sqrt(xDirection * xDirection + yDirection * yDirection)) <= 0)return;
		recursiveRay(x + xDirection,y + yDirection, (short)(lightLevel - (Math.sqrt(xDirection * xDirection + yDirection * yDirection))),xDirection,yDirection,done);
	}
	public static Image generateLightFilter(){
		BufferedImage filter = new BufferedImage(totalLightMap.length, totalLightMap[0].length, BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i < totalLightMap.length; i++) {
			for (int j = 0; j < totalLightMap[i].length; j++) {
				filter.setRGB(i , j, new Color(0,0,0,255 - (totalLightMap[i][j])).getRGB());
			}
		}
		return filter;
	}
	public static void drawLightMap(Graphics g){
		if(lightFilter != null)
			g.drawImage(lightFilter, -(int)Scene.currentScene.camera.x,- (int)Scene.currentScene.camera.y,null);
	}
}
