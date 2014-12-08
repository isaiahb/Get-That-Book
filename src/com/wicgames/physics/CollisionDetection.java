package com.wicgames.physics;

import java.util.ArrayList;

import com.sun.javafx.Utils;
import com.wicgames.wicLibrary.Vector2;

public abstract class CollisionDetection {
	//Broad Phase
	public static void BroadPhase(ArrayList<Body> bodies) {
		int length = bodies.size();
		Body a, b;
		
		for (int i = 0; i < length ; i++) {
			// if we are on the last Body in the array just quit since every other body already compared its self with it
			a = bodies.get(i); 
			a.touching.clear();
			if (i == length -1) break;
			
			if (a.canCollide) {
				for (int j = i + 1; j < length; j++) {
					b = bodies.get(j);
					if(b.canCollide)
						detect(a,b); //Checks whether they are colliding, if they are it generates the manifold
				}//Second Loop
			}
			
		}// First Loop
	}

	//Narrow Phase
	//Rectangle, Rectangle
	static void detect(Rectangle a, Rectangle b) {
		
		Vector2 minA, minB, maxA, maxB;
		minA = a.position;	maxA = Vector2.add(minA,a.size);
		minB = b.position;	maxB = Vector2.add(minB,b.size);
		
		if(maxA.x+1 < minB.x || minA.x-1 > maxB.x) return;
		if(maxA.y+1 < minB.y || minA.y-1 > maxB.y) return;
		//return true;
		
		//Collision is happening so generate manifold
		Manifold m = new Manifold(a, b);
		m.restitution = Math.min( a.restitution, b.restitution);
		
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
		//m.penetration = oX-1;
		//m.penetration = oY-1;
		//if (b.position.y < a.position.y)
		
		
		//*
		if (oX < oY) {
			m.penetration = oX-1;
			if (centerA.x > centerB.x)
				m.normal = new Vector2(-1, 0);
			else
				m.normal = new Vector2(1,0);
		} 
		else {
			m.penetration = oY-1;
			if (centerA.y > centerB.y)
				m.normal = new Vector2(0, -1);
			else
				m.normal = new Vector2(0, 1);
		}
		//*/
	}
	
	
	//Circle, Circle
	static void detect(Circle a, Circle b) {
		double r = a.radius + b.radius;
		double d = Vector2.distance(a.position,b.position);
		boolean test = (r > d);
		if (!test) return;
		
		//Collision is happening so generate manifold
		Vector2 n = Vector2.sub(b.position, a.position);
		Manifold m = new Manifold(a, b);
		m.restitution = Math.min( a.restitution, b.restitution);
		m.contactPoint = Vector2.add(a.position, Vector2.mul(n, 0.5));
		
	
		if (d != 0) {
			m.penetration = r - d;
			m.normal = Vector2.div(n, d);
			return;
		}
		// Circles are on same position
		else {
			// Choose random (but consistent) values
			m.penetration = a.radius;
			m.normal = new Vector2(1, 0);
			return;
		}
	}
	
	//Rectangle, Circle
	static void detect(Rectangle a, Circle b) {
		Vector2 n = Vector2.sub(b.position, a.position);
		Vector2 halfA = new Vector2(a.size.x/2, a.size.y/2);
		Vector2 centerA = Vector2.add(a.position, halfA);
		Vector2 v = Vector2.sub(b.position, centerA);
		Vector2 border = new Vector2();
		border.x = Utils.clamp(-halfA.x, v.x, halfA.x);
		border.y = Utils.clamp(-halfA.y, v.y, halfA.y );
		border.add(centerA);
		Vector2 distanceVector = Vector2.sub(b.position, border);
		double distance = distanceVector.magnitude() - b.radius;
		if (!(distance <= 0)) return;

		//Collision is happening so generate manifold
		Manifold m = new Manifold(a, b);
		m.penetration = Math.abs(distance);
		m.restitution = Math.min( a.restitution, b.restitution);
		
		border.x = Utils.clamp(-halfA.x, v.x, halfA.x);
		border.y = Utils.clamp(-halfA.y, v.y, halfA.y );
		boolean inside = false;
		
		if(Vector2.equals(v, border)) {
			inside = true;
			
			if(Math.abs(v.x ) > Math.abs(v.y )) {
				// Clamp to closest extent
				if(border.x > 0)
					border.x = halfA.x;
				else
					border.x = -halfA.x;
			}
				 
			// y axis is shorter
			else {
				// Clamp to closest extent
				if(border.y > 0)
					border.y = halfA.y;
				else
					border.y = -halfA.y;
			}
		}
		
		Vector2 normal = Vector2.sub(v, border);

		if(inside) 
			m.normal = new Vector2(-normal.x, -normal.y).normal();
		else
			m.normal = normal.normal();
		
	}
	
	
	
	//Body, Body
	static void detect(Body a, Body b) {
		if(a.type == "Rectangle") {
			if(b.type == "Rectangle") 
				detect((Rectangle)a, (Rectangle)b);
			
			if(b.type == "Circle") 
				detect((Rectangle)a, (Circle)b);
		}
		
		if(a.type == "Circle") {
			if(b.type == "Circle") 
				detect((Circle)a, (Circle)b);
			
			if(b.type == "Rectangle") 
				detect((Rectangle)b, (Circle)a);
		}
		
		//return detect((Rectangle)b, (Circle)a);
	}
}
