package com.wicgames.physics;

import java.awt.*;

public class Material {
	public double density, restitution, friction, staticFriction;
	public Color color;
	public Material(double density, double restitution, Color color, double friction) {
		this.density = density;
		this.restitution = restitution;
		this.color = color;
		this.friction = friction;
		this.staticFriction = 1;
	}
	
	public static Material Rock = new Material(0.6, 0.1, Color.gray,2);
	public static Material Wood = new Material(0.3, 0.2, new Color(250, 200,200),1.5);
	public static Material Metal = new Material(1.2, 0.05, Color.LIGHT_GRAY,1.7);
	public static Material BouncyBall = new Material(0.3, 0.8, Color.blue,0.8);
	public static Material SuperBall = new Material(0.3, 0.95, Color.blue,1);
	public static Material Pillow = new Material(0.6, 0.2, Color.WHITE,0.5);
	public static Material StaticIce = new Material(0.0, 0, new Color(25,200,25),0);
	public static Material Static = new Material(0.0, 0, new Color(25,200,25),0.9);
	public static Material Flesh = new Material(1, 0, new Color(100, 50,50), 0.9);
}
