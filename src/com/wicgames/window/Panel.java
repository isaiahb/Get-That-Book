package com.wicgames.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wicgames.game.Main;

public class Panel extends JPanel implements Runnable{
	public boolean running = false;
	private long last;
	private Thread loop;
	
	public Panel(JFrame frame) {
		setFocusable(true);
		this.setPreferredSize(frame.getSize());
		this.requestFocusInWindow();
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		running = true;
		loop = new Thread(this);
		loop.start();
		
	}
	
	public void pause() {
		running  = false;
	}
	public void resume() {
		running = true;
		last = System.nanoTime();
	}
	public void run(){
		last = System.nanoTime();
		double delta;
		while (true) {
			
			while (running) {
				double timeTaken = (double)(System.nanoTime() - last);
				last = System.nanoTime();
				delta = timeTaken/1000000000.00;
				
				Main.update(delta);
				repaint();
				try {
					Thread.sleep((long) (1000.0/Main.FPS));
				} 
				catch (Exception e) {}	
			}
			System.out.println("Paused, press 'p' to unpause");
			//Wait before checking if its running again
			try {
				Thread.sleep((long) (1000.0/Main.FPS));
			} 
			catch (Exception e) {}
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setColor(Color.WHITE);
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponents(graphics2D);
		graphics2D.scale(Main.scale, Main.scale);
		Main.draw(graphics2D);
		graphics2D.dispose();
	}
}
