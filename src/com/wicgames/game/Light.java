package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.wicgames.physics.Ray;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Light {
	public static short[][] lightMap;
	public static short[][] totalLightMap;
	public static ArrayList<LightSource> lightSources = new ArrayList<LightSource>();
	private static Image lightFilter;
	
	public static void refresh() {
		Ray.calculatePixels();
		totalLightMap = new short[Ray.staticPixels.length][Ray.staticPixels[0].length];
		lightMap = new short[Ray.staticPixels.length][Ray.staticPixels[0].length];

		for(LightSource light : lightSources){
			int distance = 1000;//(int)(light.intensity / 0.25);
			for (double degree = 0; degree < 360; degree+=0.0025) {
				double slope = Math.atan(Math.toRadians(degree));
				double x = Math.cos(slope);
				double y = Math.sin(slope);
				if(degree > 270) {
					x = -x; 
					y = -y;
				}
				else if (degree > 180)
					y = -y;
				else if (degree > 90)
					x = -x;
				
				Vector2 normal = new Vector2(x, y);
				//System.out.println(normal);
				Ray ray = new Ray(new Vector2(light.x,light.y),normal, distance);
				int i = 0;
				for(Vector2 point : ray.points) {
					if (point.x < 0 || point.y < 0) continue;
					lightMap[(int) point.x][(int) point.y] = (short) (i);
					i++;
				}
				//System.out.println(ray.points.size());
			}
			for(int x = 0;x < lightMap.length;x++)
				for(int y = 0;y < lightMap[x].length;y++){
					//short newIntensity = (short) Math.min((totalLightMap[x][y] + lightMap[x][y]),220);
					short intense = lightMap[x][y] <= 220 ? lightMap[x][y] : 220;
					intense = intense >= 0 ? intense : 0;
					//if(intense > 220)System.out.println(intense);
					totalLightMap[x][y] = intense;
					lightMap[x][y] = 0;
				}
		}
		lightFilter = generateLightFilter();
	}
	
	public static Image generateLightFilter(){
		BufferedImage filter = new BufferedImage(totalLightMap.length, totalLightMap[0].length, BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i < totalLightMap.length; i++) {
			for (int j = 0; j < totalLightMap[i].length; j++) {
				if(totalLightMap[i][j] > 220)System.out.println(totalLightMap[i][j]);
				filter.setRGB(i , j, new Color(0,0,0,220 - (totalLightMap[i][j])).getRGB());
			}
		}
		return filter;
	}
	
	
	public static void drawLightMap(Graphics g){
		if(lightFilter != null)
			g.drawImage(lightFilter, -(int)Scene.currentScene.camera.x,- (int)Scene.currentScene.camera.y,null);
	}
}
