package com.wicgames.physics;

import java.util.ArrayList;

import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Vector2;

public abstract class CollisionResolution {
	public static double getSeparatingVelocity(Manifold m) {
		Vector2 relativeVelocity = Vector2.sub(m.b.velocity, m.a.velocity);
		return Vector2.dotProduct(relativeVelocity, m.normal);
	}	
	public static void resolvePenetration(Manifold m) {
		Body a = m.a;
		Body b = m.b;
		a.hit(b);
		b.hit(a);
		if (m.penetration > 0.5) {
			if (!a.canCollide || !b.canCollide)
				return;
			double totalIMass = a.inverseMass + b.inverseMass;

			// Do not resolve if objects are not penetrating
			// Do not resolve if both objects have infinite mass
			if (totalIMass <= 0 || m.penetration <= 0)
				return;
			Vector2 movePerIMass = Vector2.mul(m.normal, m.penetration
					/ totalIMass); // Calculates translating vector/direction
			// Apply translation
			a.position.add(Vector2.mul(movePerIMass, -a.inverseMass));
			b.position.add(Vector2.mul(movePerIMass, b.inverseMass));
		}
	}

	public static void resolveVelocity(Manifold m) {
		Body a = m.a;
		Body b = m.b;
		//Trigger Hit Methods or w/e;
		a.hit(b);
		b.hit(a);
		double separatingVelocity = getSeparatingVelocity(m);
		double totalInverseMass = a.inverseMass + b.inverseMass;
		
		// Do not resolve if velocities are separating
		// Do not resolve if both objects have infinite mass
		if (separatingVelocity > 0 || totalInverseMass <= 0) {
			return; 
		}
		
		double restitution = 0; 
		
		// Calculates the new separating velocity
		double newSeparatingVelocity = -separatingVelocity * restitution;	
		
		// Check velocity build up due to acceleration only
		Vector2 accCausedVelocity = Vector2.add(a.acceleration, b.acceleration);
		double accCausedSepVelocity = Vector2.dotProduct(accCausedVelocity, m.normal);
		accCausedSepVelocity *= Main.delta;

		// If we’ve got a closing velocity due to acceleration buildup,
		// remove it from the new separating velocity.
		if (accCausedSepVelocity < 0) {
			newSeparatingVelocity += restitution * accCausedSepVelocity;
			
			// Make sure we haven’t removed more than was there to remove.
			if (newSeparatingVelocity < 0) 
				newSeparatingVelocity = 0;
		}
		
		double deltaVelocity = separatingVelocity - newSeparatingVelocity;	// Calculates the change in velocity
		double impulse = deltaVelocity / totalInverseMass;					// Calculates the impulse scalar/length
		Vector2 impulsePerMass = Vector2.mul(m.normal, impulse);			// Calculates the impulse vector/direction
		
		// Apply impulse
		a.velocity.add(Vector2.mul(impulsePerMass, a.inverseMass));
		b.velocity.sub(Vector2.mul(impulsePerMass, b.inverseMass));
	}

	public static void resolve(Manifold m) {
		resolvePenetration(m);
		resolveVelocity(m);
	}

	public static void update(ArrayList<Manifold> manifolds) {
		for (Manifold manifold : manifolds) {
			CollisionResolution.resolve(manifold);
			// CollisionResolution.resolveVelocity(manifold);
		}
	}
}
