package com.wicgames.window;

import com.wicgames.game.Main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private Thread thread;
	private boolean running = false;
	public Panel(JFrame frame) {
		setFocusable(true);
		this.requestFocusInWindow();
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void start() {
		running = true;
		long last = System.nanoTime();
		double delta;
		while (running) {
			double timeTaken = (double)(System.nanoTime() - last);
			last = System.nanoTime();
			delta = timeTaken/1000000000.00 ;
			Main.update(delta);
			repaint();
			try {
				Thread.sleep((long) (1000.0/Main.FPS));
			} 
			catch (Exception e) {}	
		}
	}	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.drawRect(0, 0, getWidth(), getHeight());
		Main.draw(graphics2D);
	}
}
