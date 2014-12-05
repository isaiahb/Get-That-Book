package com.wicgames.game;

import java.awt.Graphics2D;

import com.wicgames.wicLibrary.Drawable;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class Menu extends Scene {
	Button play, level;

	public Menu() {
		Main.panel.setLayout(null);
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		super.draw(graphics2D);
	}

	@Override
	public void update(double delta) {

	}
	public void init() {
		play = new Button("bin/assets/textures/PlayButton24.png", (int)Main.HALF.x, (int)Main.HALF.y);
		play.addActionListener((e) -> {
			System.out.println("Clicked");
			Scene.currentScene = new LevelMenu();
			Scene.currentScene.init();
		});
		Main.panel.add(play);
		Main.frame.pack();
		Scene.currentScene.drawables.add((Drawable) play);
		level = new Button("bin/assets/textures/LEVEL.png", (int)Main.HALF.x, (int)Main.HALF.y + 50);
		level.addActionListener((e) -> {
			Scene.currentScene = new LevelMenu();
			Scene.currentScene.init();
		});
		Main.panel.add(level);
		Main.frame.pack();
		Scene.currentScene.drawables.add((Drawable) level);
	}

}
