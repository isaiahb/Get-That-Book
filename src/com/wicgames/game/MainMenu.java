package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.wicgames.scene.Scene;

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
		play = new JButton();
		play.setBackground(new Color(0,0,0,0));
		play.setBorder(null);
		
		try {
			play.setIcon(new ImageIcon(ImageIO.read(new File("bin/assets/textures/PlayButton24.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
