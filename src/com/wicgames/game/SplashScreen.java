package com.wicgames.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.Vector2;

public class SplashScreen extends Scene {
	public Image splash;
	private double timeElapsed;
	private double showTime = 2;
	public SplashScreen() {
		try {
			splash = ImageIO.read(new File("bin/assets/textures/WIC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(splash, 0,0, (int)Main.panel.getWidth(), (int)Main.panel.getHeight(), null);
	}

	@Override
	public void update(double delta) {
		timeElapsed += delta;
		if(timeElapsed >= showTime){
			System.out.println("Moving to next scene");
			currentScene = new MainMenu();;
			currentScene.init();
		}
	}

	@Override
	public void init() {
		
	}
	
}
