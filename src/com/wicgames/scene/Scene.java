package com.wicgames.scene;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.game.Main;
import com.wicgames.gameObjects.GameObject;
import com.wicgames.physics.Body;
import com.wicgames.physics.CollisionDetection;
import com.wicgames.physics.CollisionResolution;
import com.wicgames.physics.Manifold;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Vector2;

public abstract class Scene {
	public static Scene currentScene;
	public Vector2 camera = new Vector2();
	public Vector2 size = new Vector2();
	public ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	public ArrayList<Body> bodies = new ArrayList<Body>();
	public Scene() {
		if (Scene.currentScene != null)
			Scene.currentScene.destroy();
	}
	public void update(double delta) {
		Manifold.clearManifolds();
		Body.update(bodies, delta);
		//Constraint.update(constraints);

		CollisionDetection.BroadPhase(bodies);
		CollisionResolution.update(Manifold.all);
	}

	public void draw(Graphics2D graphics2D) {
		Drawable.draw(graphics2D, drawables);
	}
	public abstract void init();
	public void destroy() {
		Main.panel.removeAll();
	}
}
