package com.wicgames.window;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.wicgames.game.Data;

public class Frame extends JFrame {
	public Frame (int width, int height, String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setTitle(title);
		try {
			setIconImage(ImageIO.read(new File("bin/assets/textures/WIC64.png"))); //Sets icon for frame
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * Adds window listener to save files when window is closed
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Data.save.saveData("bin/assets/data/save/save.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
