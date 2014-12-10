package com.wicgames.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.wicLibrary.Animation;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.window.Scene;

public class SplashScreen extends Scene implements Drawable{
	public Image splash;
	private double timeElapsed;
	private double showTime = 0.1;
	private Animation candle;
	public SplashScreen() {
		super();
		SpriteSheet animate = new SpriteSheet("bin/assets/textures/WIC64.png",64,64,1,0);
		splash = animate.getImage(0);
		candle = new Animation(animate, 0, 2, this, 0.05, -1,animate.getImage(0));
		candle.start();
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
			candle.destroy();
			candle = null;
		}
	}

	@Override
	public void init() {}

	@Override
	public void updateImage(Image texture) {
		splash = texture;
	}
}
