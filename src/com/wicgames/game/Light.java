package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Light {
	private static int size = 16;
	public static byte[][] lightMap;
	public static byte[][] totalLightMap;
	private static Image lightFilter;
	public static void generateLightMap(int x,int y){
		lightMap = new byte[x][y];
		totalLightMap = new byte[x][y];
		for (int i = 0; i < lightMap.length; i++) {
			for (int j = 0; j < lightMap[i].length; j++) {
				lightMap[i][j] = Byte.MIN_VALUE;
				totalLightMap[i][j] = 0;
			}
		}
		long startTime = System.currentTimeMillis();
		calculateLight(200,200,(byte)127);
		calculateLight(200,500,(byte)127);
		lightFilter = generateLightFilter();
		System.out.println(System.currentTimeMillis() - startTime);
	}
	public static void calculateLight(int x,int y,byte lightLevel){
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
				if((int)totalLightMap[i][j] + (int)lightMap[i][j] <= Byte.MAX_VALUE)
					totalLightMap[i][j] += lightMap[i][j];
				else
					totalLightMap[i][j] = Byte.MAX_VALUE;
			}
		}
	}
	public static void recursiveRay(int x,int y,byte lightLevel,int xDirection,int yDirection,boolean[][] done){
		if(lightLevel <= Byte.MIN_VALUE || x < 0 || x > lightMap.length - 1 || y < 0 || y > lightMap[0].length - 1)return;
		if(!done[x][y]){
			lightMap[x][y] = lightLevel;
		}
		done[x][y] = true;
		if(lightLevel - (Math.sqrt(xDirection * xDirection + yDirection * yDirection)) <= Byte.MIN_VALUE)return;
		recursiveRay(x + xDirection,y + yDirection, (byte)(lightLevel - (Math.sqrt(xDirection * xDirection + yDirection * yDirection))),xDirection,yDirection,done);
	}
	public static Image generateLightFilter(){
		BufferedImage filter = new BufferedImage(totalLightMap.length, totalLightMap[0].length, BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i < totalLightMap.length; i++) {
			for (int j = 0; j < totalLightMap[i].length; j++) {
				filter.setRGB(i , j, new Color(0,0,0,255 - (totalLightMap[i][j] + 128)).getRGB());
			}
		}
		return filter;
	}
	public static void drawLightMap(Graphics g){
		if(lightFilter != null)
			g.drawImage(lightFilter, -(int)Scene.currentScene.camera.x,- (int)Scene.currentScene.camera.y,null);
	}
}
