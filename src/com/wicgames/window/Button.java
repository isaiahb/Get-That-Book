package com.wicgames.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Vector2;

public class Button extends JButton implements Drawable{
	public Image texture;
	public Vector2 position;
	public Button() {
		setBackground(new Color(0,0,0,0));
		setBorder(null);
	}
	public Button(String path,Vector2 position) {
		setBackground(new Color(0,0,0,0));
		setBorder(null);
		this.position = position;
		try {
			texture = (ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		position.sub(texture.getWidth(null) / 2,texture.getHeight(null) / 2);
		super.setBounds(texture.getWidth(null) * Main.scale,texture.getHeight(null) * Main.scale,(int)position.x * Main.scale,(int)position.y * Main.scale);
	}
	public Button(Image texture, Vector2 position){
		setBackground(new Color(0,0,0,0));
		setBorder(null);
		this.position = position;
		this.texture = texture;
		position.sub(texture.getWidth(null) / 2,texture.getHeight(null) / 2);
		super.setBounds(texture.getWidth(null) * Main.scale,texture.getHeight(null) * Main.scale,(int)position.x * Main.scale,(int)position.y * Main.scale);
	}
	public void paint(Graphics g){}
	public void paintComponent(Graphics g){}
	public void draw(Graphics g){
		g.drawImage(texture,(int)position.x,(int)position.y,null);
	}
}
