package com.wicgames.physics;

import java.awt.*;
import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;

//import java.awt.*;

public class Rectangle extends Body {
	public static ArrayList<Rectangle> all = new ArrayList<Rectangle>();
	public static String type = "Rectangle";

	//Constructor
	public Rectangle(int x, int y, int width, int height) {
		size = new Vector2(width, height);
		position = new Vector2(x, y);
		restitution = 0.5;
		setMaterial(Material.Wood);
		
		all.add(this);
		Body.all.add(this);
		Body.all.get(Body.all.size()-1).type = type;
		Body.bodyCreated(this);
	}
	
	//Get Area
	public double getArea() {
		return (size.x * size.y)/100.;
	}
		
	//Get Center
	public Vector2 center() {
		return new Vector2(position.x + size.x/2, position.y + size.y/2);
	}
	
	public void setInertia() {
		inertia = (1/12) * mass * (Math.pow(size.x, 2) + Math.pow(size.y, 2));
	}
	

	
	//Draws Rectangle
	public void draw(Graphics2D graphics2D) {

	}
	
	//Updates All Rectangles
	public static void updateAll(double delta) {
		
		for (Rectangle rectangle: all) {
			rectangle.update(delta);
		}
	}
	//Draws All Rectangles
	public static void drawAll(Graphics2D graphics2D) {
	}

	@Override
	public boolean onTop(Body b) {
		if(position.y + size.y - 6 <= b.position.y + 6)
				return true;
		return false;
	}

	@Override
	public boolean leftOf(Body b) {
		if(position.x + 5 >= b.position.x + b.size.x)
			return true;
		return false;
	}

	@Override
	public boolean rightOf(Body b) {
		if(position.x + size.x - 5 <= b.position.x)
			return true;
		return false;
	}
}