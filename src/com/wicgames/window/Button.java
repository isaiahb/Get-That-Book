package com.wicgames.window;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
	Image texture;
	public Button() {
		setBackground(new Color(0,0,0,0));
		setBorder(null);
	}
	public Button(String path) {
		setBackground(new Color(0,0,0,0));
		setBorder(null);
		try {
			setIcon(new ImageIcon(ImageIO.read(new File(path))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
