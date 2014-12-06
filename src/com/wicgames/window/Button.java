package com.wicgames.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonListener;

import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Vector2;

public class Button extends JButton implements Drawable{
	public Image texture;
	public Vector2 position;
	private boolean rolledOver = false,pressed = false, primed = false;
	public Button() {
		this.position = new Vector2();
		setBackground(new Color(0,0,0,0));
		setBorder(null);
	}
	public Button(String path, int x, int y) {
		setBackground(new Color(0,0,0,0));
		setBorder(null);
		this.position = new Vector2(x,y);
		try {
			texture = (ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		position.sub(texture.getWidth(null) / 2,texture.getHeight(null) / 2);
		super.setBounds((int)(position.x * Main.scale),(int)(position.y * Main.scale),(int)(texture.getWidth(null) * Main.scale),(int)(texture.getHeight(null) * Main.scale));
		setRolloverEnabled(false);
		removeMouseListener(getMouseListeners()[0]);
		addMouseListener(new BasicButtonListener(this){
			@Override
			public void mousePressed(MouseEvent e) {
				if(!primed)
					primed = true;
				pressed = true;
			}@Override
			public void mouseReleased(MouseEvent e) {
				if(primed){
					for(ActionListener al : getActionListeners())
						al.actionPerformed(new ActionEvent(model,1,model.getActionCommand()));
					primed = false;
				}
				pressed = false;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				primed = true;
				rolledOver = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				primed = false;
				rolledOver = false;
			}
		});
	}
	public Button(Image texture, int x, int y){
		setBackground(new Color(0,0,0,0));
		setBorder(null);
		this.position = new Vector2(x, y);
		this.texture = texture;
		position.sub(texture.getWidth(null) / 2,texture.getHeight(null) / 2);
		super.setBounds((int)(position.x * Main.scale),(int)(position.y * Main.scale),(int)(texture.getWidth(null) * Main.scale),(int)(texture.getHeight(null) * Main.scale));
		setRolloverEnabled(false);
		removeMouseListener(getMouseListeners()[0]);
		addMouseListener(new BasicButtonListener(this){
			@Override
			public void mousePressed(MouseEvent e) {
				if(!primed)
					primed = true;
				pressed = true;
			}@Override
			public void mouseReleased(MouseEvent e) {
				if(primed){
					for(ActionListener al : getActionListeners())
						al.actionPerformed(new ActionEvent(model,1,model.getActionCommand()));
					primed = false;
				}
				pressed = false;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				primed = true;
				rolledOver = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				primed = false;
				rolledOver = false;
			}
		});
	}
	public void paint(Graphics g){}
	public void paintComponent(Graphics g){}
	public void draw(Graphics2D g){
		g.drawImage(texture,(int)position.x,(int)position.y,null);
	}
}
