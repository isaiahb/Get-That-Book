package com.wicgames.window;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame (int width, int height, String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setTitle(title);
		try {
			setIconImage(ImageIO.read(new File("bin/WIC64.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
