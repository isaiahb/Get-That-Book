package com.wicgames.physics;

import java.util.ArrayList;
import com.wicgames.wicLibrary.Vector2;

public class Manifold {
	public static ArrayList<Manifold> all = new ArrayList<Manifold>();
	public static void clearManifolds() {all = new ArrayList<Manifold>();}
	
	public Body a, b;
	public Vector2 normal, contactPoint;
	public double penetration;
	public boolean isConstraint = false;
	
	public Manifold(Body a, Body b) {
		this.a = a;
		this.b = b;
		all.add(this);
	}

}