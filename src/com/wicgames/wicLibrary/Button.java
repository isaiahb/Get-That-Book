package com.wicgames.wicLibrary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Button {
	public Vector2 position = new Vector2();
	public Vector2 size = new Vector2();
	private ClickListener clickListen;
	private Image texture;
	
	public Button (int x,int y, int width,int height) {
		position.setTo(x,y);
		size.setTo(width, height);
	}
	
	public void setTexture(String texture){
		try {
			this.texture = ImageIO.read(new File(texture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawComponent(Graphics g){
		if(texture != null)
			g.drawImage(texture,(int)position.x,(int)position.y,(int)size.x,(int)size.y,null);
		else{
			g.setColor(Color.PINK);
			g.fillRect((int)position.x, (int)position.y, (int)size.x, (int)size.y);
		}
	}
	public void draw(Graphics2D g) {}
	public void addClickListener(ClickListener cl){
		clickListen = cl;
		ClickListener.clicks.add(cl);
	}
}
