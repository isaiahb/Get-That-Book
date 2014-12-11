package com.wicgames.physics;

import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Force {
	public static ArrayList<Force> all = new ArrayList<Force>();
	public static void created (Force force) {
		//System.out.println(Scene.currentScene);
		Scene.currentScene.forces.add(force);
	}
	public Force(Vector2 force) {
		this.force = force;
		created(this);
	}
	public static void updateAll() {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).update();
		}
	}
	public static void update(ArrayList<Force> forces) {
		for (int i = 0; i < forces.size(); i++) {
			forces.get(i).update();
		}
	}	
	public Vector2 force;
	public ArrayList<Body> bodies = new ArrayList<Body>();
	public void add(Body body) {bodies.add(body);}
	public void remove(Body body) {bodies.remove(body);}
	public void updateForce(Body body){
		body.addForce(force);
	}
	
	public void update() {
		for (int i = 0; i < bodies.size(); i ++) {
			updateForce(bodies.get(i));
		}
	}
	
	
	  //////////////////////////////////////////////////
	 //				Force Generators				///
	//////////////////////////////////////////////////
	
	//Gravity//
	public static class Gravity extends Force {
		public Gravity (double x, double y) {
			super(new Vector2(x,y));
			
			created(this);
			all.add(this);
		}
		
		public void updateForce(Body body) {
			if(body.affectedGravity)
				body.addForce(Vector2.mul(force, body.mass));
		}
		
	}
	
	//Spring//
	public static class Spring extends Force {
		Body other;
		double springConstant, length;
		public Spring (Body other, double springConstant, double length) {
			super(new Vector2(0,0));
			this.other = other;
			this.springConstant = springConstant;
			this.length = length;
			all.add(this);
		}
		
		public void updateForce(Body body) {
			force = body.position.copy();
			force.sub(other.position);
			double magnitude = force.magnitude();
			magnitude = Math.abs(magnitude - length);
			magnitude *= springConstant;
			force.normalize();
			//force.mul(magnitude);
			body.addForce(Vector2.mul(force,-magnitude));
		}
		
	}
	
}


