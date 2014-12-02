package com.wicgames.window;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame (int width, int height, String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setTitle(title);
	}
}
