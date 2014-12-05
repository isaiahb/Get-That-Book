package com.wicgames.game;

import java.awt.Graphics2D;

import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Frame;
import com.wicgames.window.Panel;
import com.wicgames.window.Scene;

public class Main {
	public static final String title = "Get That Book";
	public static final int FPS = 60;
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	public static final Vector2 HALF = new Vector2(WIDTH/2, HEIGHT/2);
	public static final int TILESIZE = 32;
	public static double scale = Double.parseDouble(Data.config.getValue("scale"));
	public static Frame frame = new Frame((int)(WIDTH*scale),(int)(HEIGHT*scale), title);
	public static Panel panel = new Panel(frame);
	
	public static void main(String[] args) {
		//Main Method
		panel.start();								//Starts the game loop
		Scene.currentScene = new SplashScreen();	//Sets the current Scene to the splash screen
	}
	public static void update(double delta) {
		//Updates the current scene if it exists
		if (Scene.currentScene != null)
			Scene.currentScene.update(delta);
	}
	public static void draw(Graphics2D graphics2D) {
		//Draws the current scene if it exists
		if (Scene.currentScene != null)
			Scene.currentScene.draw(graphics2D);
	}
	
}
