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

		for (LightSource light : lightSources) {
			int distance = (int) (light.intensity / 0.25);
			for (double degree = 0; degree < 360; degree += 0.008) {
				double slope = Math.tan(Math.toRadians(degree));
				double x = Math.cos(slope);
				double y = Math.sin(slope);
				// if(degree > 270) {
				// x = -x;
				// y = -y;
				// }
				// else if (degree > 180)
				// x = -x;
				// else if (degree > 90)
				// y = -y;
				Vector2 normal = new Vector2(x, y);
				Ray ray = new Ray(new Vector2(light.x, light.y), normal,
						distance);
//				if (ray.hit) { WIP
//					Vector2 tileStart = Vector2.div(ray.endPosition, 32);
//						for (int tileX = 0; tileX < 32; tileX++)
//							for (int tileY = 0; tileY < 32; tileY++){
//								int posX = ((int)tileStart.x) * 32 + tileX;
//								int posY = ((int)tileStart.y) * 32 + tileY;
//								if(posX < 0 || posY < 0 || posX > lightMap.length-1 || posY > lightMap[0].length-1)continue;
//								lightMap[posX][posY] = 220;
//							}
//								
//				}
				int i = 0;
				for (Vector2 point : ray.points) {
					if (point.x < 0 || point.y < 0
							|| point.x > lightMap.length - 1
							|| point.y > lightMap[0].length - 1)
						continue;
					lightMap[(int) point.x][(int) point.y] = (short) (220);
					i++;
				}
			}
			for (int x = 0; x < lightMap.length; x++)
				for (int y = 0; y < lightMap[x].length; y++) {
					// short newIntensity = (short)
					// Math.min((totalLightMap[x][y] + lightMap[x][y]),220);
					short intense = (short) (lightMap[x][y]
							+ totalLightMap[x][y] <= 220 ? lightMap[x][y]
							+ totalLightMap[x][y] : 220);
					intense = intense >= 0 ? intense : 0;
					// if(intense > 220)System.out.println(intense);
					totalLightMap[x][y] = intense;
					lightMap[x][y] = 0;
				}
		}
		lightFilter = generateLightFilter();
	}

	public static Image generateLightFilter() {
		BufferedImage filter = new BufferedImage(totalLightMap.length,
				totalLightMap[0].length, BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i < totalLightMap.length; i++) {
			for (int j = 0; j < totalLightMap[i].length; j++) {
				filter.setRGB(i, j, new Color(0, 0, 0,
						220 - (totalLightMap[i][j])).getRGB());
			}
		}
		return filter;
	}

	public static void drawLightMap(Graphics g) {
		if (lightFilter != null)
			g.drawImage(lightFilter, -(int) Scene.currentScene.camera.x,
					-(int) Scene.currentScene.camera.y, null);
	}
}
