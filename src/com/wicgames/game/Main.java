package com.wicgames.game;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Frame;
import com.wicgames.window.Panel;

public class Main {
	public static final String title = "The Quest For The Lex";
	public static final int FPS = 60;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static int scale = 1;
	public static Frame frame = new Frame(WIDTH*scale, HEIGHT*scale, title);
	public static Panel panel = new Panel(frame);
	public static void main(String[] args) {
		//Main Method
		panel.start();
		Scene.currentScene = new SplashScreen();

	}
	
	public static void update(double delta) {
		Scene.updateScene(delta);
	}
	public static void draw(Graphics2D graphics2D) {
		Scene.drawScene(graphics2D);
	}
	
    public static GridBagConstraints getConstraints(int x,int y,int width,int height){
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = x;
    	c.gridy = y;
    	c.gridwidth = width;
    	c.gridheight = height;
        return c;
    }
}
