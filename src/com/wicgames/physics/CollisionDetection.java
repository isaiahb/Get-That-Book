package com.wicgames.physics;

import java.util.ArrayList;

import com.wicgames.wicLibrary.Vector2;

public abstract class CollisionDetection {
	public static int reset = 0;
	//Broad Phase
	public static void BroadPhase(ArrayList<Body> bodies) {
		reset++;
		reset %= 2;
		int length = bodies.size();
		Body a, b;
		
		for (int i = 0; i < length ; i++) {
			// if we are on the last Body in the array just quit since every other body already compared its self with it
			a = bodies.get(i);
			if (reset == 0) 
				a.touching.clear();
			
			if (i == length -1) 
				break;
			
			for (int j = i + 1; j < length; j++) {
				b = bodies.get(j);
				detect(a,b); //Checks whether they are colliding, if they are it generates the manifold
			}
			
		}
	}

	//Narrow Phase
	//Rectangle, Rectangle
	static void detect(Body a, Body b) {
		
		Vector2 minA, minB, maxA, maxB;
		minA = a.position;	maxA = Vector2.add(minA,a.size);
		minB = b.position;	maxB = Vector2.add(minB,b.size);
		
		if(maxA.x < minB.x || minA.x > maxB.x) return;
		if(maxA.y < minB.y || minA.y > maxB.y) return;
		//return true;
		
		//Collision is happening so generate manifold
		Manifold m = new Manifold(a, b);
		
		Vector2 halfExtentsA = Vector2.div(a.size, 2);
		Vector2 halfExtentsB = Vector2.div(b.size, 2);
		Vector2 centerA = Vector2.add(a.position, halfExtentsA);
		Vector2 centerB = Vector2.add(b.position, halfExtentsB);
		
		double hX = halfExtentsA.x + halfExtentsB.x;
		double hY = halfExtentsA.y + halfExtentsB.y;
		double dX = Math.abs(centerA.x - centerB.x);
		double dY = Math.abs(centerA.y - centerB.y);
		double oX = Math.abs(dX - hX);
		double oY = Math.abs(dY - hY);
		
		if (oX < oY) {
			m.penetration = oX;
			if (centerA.x > centerB.x)
				m.normal = new Vector2(-1, 0);
			else
				m.normal = new Vector2(1,0);
		} 
		else {
			m.penetration = oY;
			if (centerA.y > centerB.y)
				m.normal = new Vector2(0, -1);
			else
				m.normal = new Vector2(0, 1);
		}
	}
	
	
	
}
