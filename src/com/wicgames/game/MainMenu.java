package com.wicgames.game;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.Vector2;

public class MainMenu extends Scene {
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
		//Main.panel.add(play,Main.getConstraints(2, 2, 2, 1));
	}
}
