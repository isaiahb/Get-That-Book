package com.wicgames.game;

import java.awt.Graphics2D;

import com.wicgames.wicLibrary.Drawable;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class Menu extends Scene {
	Button play, level, options;

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
			Scene.currentScene = new LevelScene(Integer.parseInt(Data.save.getValue("currentLevel")));
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
		options = new Button("bin/assets/textures/OPTION.png", (int)Main.HALF.x, (int)Main.HALF.y + 100);
		options.addActionListener((e) ->{
			Scene.currentScene = new OptionMenu();
			Scene.currentScene.init();
		});
		Main.panel.add(level);
		Main.panel.add(options);
		Main.frame.pack();
		Scene.currentScene.drawables.add((Drawable) level);
		Scene.currentScene.drawables.add((Drawable) options);
	}

}
