package com.wicgames.scene;

import java.awt.Graphics;
import java.util.ArrayList;

import com.wicgames.gameObjects.GameObject;
import com.wicgames.wicLibrary.Vector2;

public abstract class Scene {
	public Vector2 camera = new Vector2();
	public Vector2 size = new Vector2();
	public static Scene currentScene;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public abstract void draw(Graphics g);
	public abstract void update(double delta);
	public abstract void init();
	
	public static void updateScene(double delta) {
		if (currentScene != null) {
			currentScene.update(delta);
			for (int i = 0; i < currentScene.objects.size(); i++) {
				// currentScene.objects.get(i);
			}
		}
	}

	public static void drawScene(Graphics g) {
		if (currentScene != null) {
			currentScene.draw(g);
			for (int i = 0; i < currentScene.objects.size(); i++) {
				currentScene.objects.get(i).draw(g);
			}
		}
	}
}
