package com.wicgames.physics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;

public class Circle extends Body {
	public static ArrayList<Circle> all = new ArrayList<Circle>();
	public static String type = "Circle";

	// Constructor
	public Circle(int x, int y, int r) {
		
		radius = r;
		position = new Vector2(x, y);
		size = new Vector2(r,r);
		restitution = 0.5;
		
		setMaterial(Material.SuperBall);
		//ssetMass(getArea());

		all.add(this);
		Body.all.add(this);
		Body.all.get(Body.all.size()-1).type = type;
		Body.bodyCreated(this);
	}

	
	
	//Get Area
	public double getArea() {
		return (double) (Math.PI * (radius * radius))/100. ;
	}
	public Vector2 center() {
		return position.copy();
	}

	public void setInertia() {
		inertia = (1/12) * mass * (Math.pow(radius, 2)*2);
	}
	

	// Draws Rectangle
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		graphics2D.fillOval((int) position.x - radius, (int) position.y - radius, radius * 2, radius * 2);

		graphics2D.setColor(Color.BLACK);
		graphics2D.drawOval((int) position.x - radius, (int) position.y - radius, radius * 2, radius * 2);
		
	}

	// Updates All Rectangles
	public static void updateAll(double delta) {
		for (Circle circle : all) {
			circle.update(delta);
		}
	}

	// Draws All Circles
	public static void drawAll(Graphics2D graphics2D) {
		for (Circle circle : all) {
			circle.draw(graphics2D);
		}
	}



	@Override
	public boolean onTop(Body b) {
		if(center().y + size.y / 2  <= b.position.y)
			return true;
		return false;
	}



	@Override
	public boolean leftOf(Body b) {
		return false;
	}



	@Override
	public boolean rightOf(Body b) {
		return false;
	}
}
