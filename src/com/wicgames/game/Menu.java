package com.wicgames.game;

import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Button;

public class Menu extends Scene {
	//Button play;
	JButton play;
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
	
	public void init(){
		play = new Button("bin/assets/textures/PlayButton24.png", new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked");
				Scene.currentScene = new Level1();
			}
		});
		Main.panel.add(play);
		Main.frame.pack();
		Scene.currentScene.drawables.add((Drawable) play);
	}

}
