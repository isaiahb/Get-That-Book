package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.input.Key;
import com.wicgames.input.Mouse;
import com.wicgames.physics.Ray;
import com.wicgames.wicLibrary.Function;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Frame;
import com.wicgames.window.Panel;
import com.wicgames.window.Scene;

public class Main {
	public static final String title = "Get That Book";
	public static final int FPS = 200;
	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	public static final Vector2 HALF = new Vector2(WIDTH/2, HEIGHT/2);
	public static final int TILESIZE = 32;
	public static double scale;
	public static Frame frame;
	public static Panel panel;
	public static double delta;
	public static void main(String[] args) {
		//Main Method
		init();
	}
	public static void init(){
		scale = Double.parseDouble(Data.config.getValue("scale"));
		frame = new Frame((int)(WIDTH*scale),(int)(HEIGHT*scale), title);
		panel = new Panel(frame);
		Scene.currentScene = new SplashScreen();	//Sets the current Scene to the splash screen
		Key.init();		//Initializes Key Class
		Mouse.init();
		Key.typed.connect(new Function(){
			public <T>void call(T c) {
				if ((char)c == 'p') {
					if (panel.running)
						panel.pause();
					else
						panel.resume();
				}
			}
		});
	}
	public static void update(double delta) {
		Main.delta = delta;
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
