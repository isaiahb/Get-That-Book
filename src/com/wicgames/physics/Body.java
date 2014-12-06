package com.wicgames.physics;

import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;

public class Body {
	public Vector2 size = new Vector2();
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Vector2 acceleration = new Vector2();
	public double mass;
	public double inverseMass;
	public boolean canCollide = true;
	public ArrayList<Body> touching = new ArrayList<Body>();
	public ArrayList<HitListeners> hitListeners = new ArrayList<HitListeners>();
	public void hit(Body body) {
		if (!touching.contains(body)) {
			touching.add(body);
			for (int i = 0; i < hitListeners.size(); i++)
				hitListeners.get(i).hit(body);
		}	
	}
	
	//Constructors
	public Body (int x, int y, int width, int height) {
		position.setTo(x,y);
		size.setTo(width, height);
	}
	public Body (Vector2 position, Vector2 size) {
		this.position.setTo(position);
		this.size.setTo(size);
	}
	
	public void setMass(double mass) {
		this.mass = mass; 
		if(mass != 0)
			inverseMass = 1/mass;
		else
			inverseMass = 0;
	}
	public void anchor() {
		setMass(0);
	}
	public void unAnchore() {
		setMass(1);
	}
	
	//Update Method
	public void update(double delta) {
		velocity.add(acceleration, delta);
		position.add(velocity, delta);	
	}
	public static void update(ArrayList<Body> bodies, double delta) {
		for (int i = 0; i < bodies.size(); i++) {
			bodies.get(i).update(delta);
		}
	}
	public boolean onTop(){
		for(Body b : touching)
			if(b.position.y >= position.y + size.y - 2)return true;
		return false;
	}
}
