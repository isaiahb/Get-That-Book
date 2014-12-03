package com.wicgames.physics;
import java.util.ArrayList;
import com.wicgames.wicLibrary.Vector2;

public abstract class CollisionResolution {

	public static void resolvePenetration(Manifold m) {
		Body a = m.a;
		Body b = m.b;
		a.hit(b);
		b.hit(a);
		if (!a.canCollide || !b.canCollide) return;
		double totalIMass = a.inverseMass + b.inverseMass;
		
		// Do not resolve if objects are not penetrating
		// Do not resolve if both objects have infinite mass
		if (totalIMass <= 0 || m.penetration <= 0) return;
		Vector2 movePerIMass = Vector2.mul(m.normal, m.penetration/totalIMass);	// Calculates translating vector/direction
		
		// Apply translation
		a.position.add(Vector2.mul(movePerIMass, -a.inverseMass)); 
		b.position.add(Vector2.mul(movePerIMass, b.inverseMass));
		
	}
	public static void resolve(Manifold m) {
		resolvePenetration(m);
	}

	public static void update(ArrayList<Manifold> manifolds) {
		for (Manifold manifold: manifolds) {
			CollisionResolution.resolve(manifold);
			//CollisionResolution.resolveVelocity(manifold);
		}
	}
}
