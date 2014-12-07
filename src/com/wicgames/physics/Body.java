package com.wicgames.physics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;

public abstract class Body {
	public static ArrayList<Body> all = new ArrayList<Body>();
	public static void bodyCreated(Body body) {
		//Main.gravity.add(body);
	}
	
	public String type = "Body";
	public Material material;
	public Vector2 size;
	public Vector2 position;
	public Vector2 velocity = new Vector2();
	public Vector2 acceleration = new Vector2();
	public Vector2 force = new Vector2();
	
	public double mass;
	public double inverseMass;
	public double restitution; 
	public double damping = 0.998;
	
	//rotation
	public double inertia = 0;
	public double inverseInertia = 0;
	public double torque = 0;
	public double angularAcceleration = 0;
	public double angularVelocity = 0;
	public double orientation = 0;
	
	//Friction 
	public double staticFriction = 0.5;
	public double dynamicFriction = 0.3;
	
	public double delta = 0;
	public int radius;
	public Color color = new Color(50,150,250);
	public boolean canCollide = true;
	
	public abstract void draw(Graphics2D graphics2D);
	public abstract double getArea();
	public abstract void setInertia();
	public abstract Vector2 center();	
	
	//Fix Mass
	public void fixMass(double x, double y, double scale){
		double area = ((x*scale) * (y*scale))/100;
		setMass(material.density * area);
	}
	//Sets Material
	public void setMaterial(Material material) {
		setMass(material.density * getArea());
		this.material = material;
		this.restitution = material.restitution;
		this.color = material.color;
		this.staticFriction = material.staticFriction;
		this.dynamicFriction = material.friction;
	}
	
	//Sets Mass
	public void setMass(double m) {
		mass = m;
		setInertia();
		
		if (mass == 0) 
			inverseMass = 0;
		else
			inverseMass = 1/mass;
	}
	
	public void addForce(Vector2 force) {
		this.force.add(force);
	}
	public void addForce(double x, double y) {
		force.add(new Vector2(x, y));
	}
	
	public ArrayList<Body> touching = new ArrayList<Body>();
	public ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
	public void addHitListener(HitListener listener) {
		hitListeners.add(listener);
	}
	public void hit (Body body) {
		touching.add(body);
		int size = hitListeners.size();
		if (size == 0 ) return;
		for (int i = 0; i < size; i++) {
			hitListeners.get(i).hit(body);
		}
	}
	
	
	//////////////////////////
	//	update functions	//
	//////////////////////////
	public void update(double delta) {
		this.delta = delta;
		acceleration.setTo(force);
		acceleration.mul(inverseMass);
		velocity.add(Vector2.mul(acceleration, delta));
		position.add(Vector2.mul(velocity, delta));
		
		//velocity.mul(damping);
		//velocity.mul(Math.pow(damping, delta));
		
		angularVelocity += torque * inverseInertia * delta;
		orientation += angularVelocity * delta;
		
		force.reset();
		torque = 0;
	}

	public static void updateAll(double delta) {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).update(delta);
		}
	}
	public static void drawAll(Graphics2D graphics2D) {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).draw(graphics2D);
		}
	}
	public static void update(ArrayList<Body> bodies, double delta) {
		for (int i = 0; i < bodies.size(); i++) {
			bodies.get(i).update(delta);
		}
	}
	public static void draw(ArrayList<Body> bodies, Graphics2D graphics2D) {
		for (int i = 0; i < bodies.size(); i++) {
			bodies.get(i).draw(graphics2D);
		}
	}
	public abstract boolean onTop();
	///////////////////////////////////////
	//		Orientation functions 		///
	///////////////////////////////////////
	
	///////////////////////////////////////
	//			Get Vertices			///
	///////////////////////////////////////

}