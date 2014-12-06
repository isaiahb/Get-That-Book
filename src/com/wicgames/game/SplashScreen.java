package com.wicgames.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.window.Scene;

public class SplashScreen extends Scene {
	public Image splash;
	private double timeElapsed;
	private double showTime = 0.5;
	public SplashScreen() {
		super();
		try {
			splash = ImageIO.read(new File("bin/assets/textures/WIC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.drawImage(splash, 0,0, (int)Main.WIDTH, (int)Main.HEIGHT, null);
	}

	@Override
	public void update(double delta) {
		super.update(delta);
		timeElapsed += delta; //Adds delta to time elapsed
		if(timeElapsed >= showTime){//If scene has been open longer than the time it is supposed to be showed move to menu screen
			System.out.println("Moving to next scene");
			currentScene = new Menu();
			currentScene.init();
		}
	}

	@Override
	public void init() {}
}
