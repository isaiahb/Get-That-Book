package com.wicgames.game;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.wicgames.scene.Scene;
import com.wicgames.window.Button;

public class MainMenu extends Scene {
	//Button play;
	JButton play;
	public MainMenu() {
		Main.panel.setLayout(new GridBagLayout());
	}

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public void update(double delta) {

	}
	
	public void init(){
		play = new Button("bin/assets/textures/PlayButton24.png");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked");
			}
		});
		Main.panel.add(play,Main.getConstraints(2, 2, 2, 1));
		Main.frame.pack();
	}
}
