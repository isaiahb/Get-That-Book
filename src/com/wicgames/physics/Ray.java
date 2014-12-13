package com.wicgames.physics;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.game.LevelScene;
import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Vector2;

public class Ray {
	public static boolean[][] staticPixels;
	public static boolean[][] dynamicPixels;
	
	public boolean hit;			//Whether the ray hit something in the distance it traveled or not
	public Vector2 endPosition;	//if it hit something this 
	public Vector2 startPosition;	//the position to start casting the ray
	public Vector2 direction;		//the vector direction which the ray is casted		
	public int maxLength;			//the max distance to test whether 
	public int length;
	public ArrayList<Vector2> points = new ArrayList<Vector2>();
	public Ray(Vector2 startPosition, Vector2 direction, int length) {
		this.startPosition = startPosition;
		this.direction = direction;
		this.maxLength = length;
		calculate();
	}
	
	private void calculate() {
		direction.normalize();
		Vector2 target = Vector2.add(startPosition, (Vector2.mul(direction, maxLength)));
		int testLength = isObsticle(startPosition, target, staticPixels, points,10);
		if (testLength == maxLength) {
			hit = false;
			endPosition = target;
			length = testLength;
		}
		else {
			hit = true;
			endPosition = Vector2.add(startPosition, (Vector2.mul(direction, testLength)));
			length = testLength;
		}
		
	}
	
	public static ArrayList<Vector2> line(int x, int y, int x2, int y2, int width) {
		int dx = Math.abs(x2-x);	//total change in x position
		int dy = Math.abs(y2-y);	//total change in y position
		int sx = x < x2 ? 1 : -1;	//direction to change x by
		int sy = y < y2 ? 1 : -1;	//direction to change y by
		int err = dx-dy;			//idk??WTF

		ArrayList<Vector2> points = new ArrayList<Vector2>();	//list of all the points in the line
		while (true) {
			
			if (x == x2 && y == y2) break;	//if the current x is the end position then break out of the loop
			int e2 = 2*err;
			if (e2 > -dy) {
				err = err - dy;
				x = x + sx;
			}
			if (e2 < dx) {
				err = err + dx;
				y = y + sy;
			}
		}
		return points;
	}
	
	public static int isObsticle(Vector2 a, Vector2 b, boolean[][] pixels, ArrayList<Vector2> points,int width) {
		return isObsticle((int)a.x, (int)a.y, (int)b.x, (int)b.y, pixels, points,width);
	}
	public static int isObsticle(int x, int y, int x2, int y2, boolean[][] pixels,ArrayList<Vector2> points,int width) {
		int dx = Math.abs(x2-x);	//total change in x position
		int dy = Math.abs(y2-y);	//total change in y position
		int sx = x < x2 ? 1 : -1;	//direction to change x by
		int sy = y < y2 ? 1 : -1;	//direction to change y by
		int err = dx-dy;			//idk??WTF
		int startX = x;
		int startY = y;
		
		while (true) {
			for(int i = -width / 2;i < Math.ceil(width / 2.);i++)
				points.add(new Vector2(x + i, y + i));
			if (x < 0 || y < 0 || x > staticPixels.length -1 || y > staticPixels[0].length-1) return (int) Math.sqrt((x2 - startX) * (x2 - startX) + (y2 - startY) * (y2 - startY));
			if (pixels[x][y] == true) {
				return (int) Math.sqrt((x - startX) * (x - startX) + (y - startY) * (y - startY));
			}			
			if (x == x2 && y == y2) break;	//if the current x is the end position then break out of the loop
			int e2 = 2*err;
			if (e2 > -dy) {
				err = err - dy;
				x = x + sx;
			}
			if (e2 < dx) {
				err = err + dx;
				y = y + sy;
			}

		}
		return (int) Math.sqrt((x - startX) * (x - startX) + (y - startY) * (y - startY));
	}
	
	
	
	
	public static void drawLine(Graphics2D g, ArrayList<Vector2> line) {
		for (int i = 0; i < line.size(); i++) {
			int x = (int) line.get(i).x;
			int y = (int) line.get(i).y;
			g.fillRect(x, y, 1, 1);
		}
	}
	
	
	
	
	
	
	
	
	public static void calculatePixels() {
		boolean[][] tiles = LevelScene.currentLevel.solid;
		staticPixels = new boolean[tiles.length*Main.TILESIZE][tiles[0].length * Main.TILESIZE];
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if (tiles[x][y]) {
					for (int i = 0; i < 32; i++) {
						for (int j = 0; j < 32; j++) {
							staticPixels[x * Main.TILESIZE + i][y * Main.TILESIZE + j] = true;
						}
					}
				}
			}
		}
	}
}
