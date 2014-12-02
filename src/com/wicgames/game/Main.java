package com.wicgames.game;

import java.awt.Graphics2D;

import com.wicgames.scene.Scene;
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
	}
	
	public static void update(double delta) {
		Scene.updateScene(delta);
	}
	public static void draw(Graphics2D graphics2D) {
		Scene.drawScene(graphics2D);
	}

}
