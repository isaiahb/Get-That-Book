package com.wicgames.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.wicLibrary.Vector2;

public class SplashScreen extends Scene {
	public Image splash;
	private double timeElapsed;
	private long showTime;
	private Scene nextScene;
	public SplashScreen(Vector2 size, Vector2 camera, String splashPath,long showTime) {
		super(size, camera);
		this.showTime = showTime;
		try {
			splash = ImageIO.read(new File(splashPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(splash,(int) size.x / 2 - splash.getWidth(null) / 2,(int) size.y / 2 - splash.getHeight(null) / 2, null);
	}

	@Override
	public void update(double delta) {
		timeElapsed += delta;
		if(timeElapsed > showTime){
			currentScene = nextScene;
		}
	}
	
}
