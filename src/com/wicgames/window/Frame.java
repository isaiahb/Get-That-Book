package com.wicgames.window;

import java.awt.Insets;
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
			System.out.println(new File("bin/assets/textures/WIC64.png").getAbsolutePath());
			setIconImage(ImageIO.read(new File("bin/assets/textures/WIC64.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
