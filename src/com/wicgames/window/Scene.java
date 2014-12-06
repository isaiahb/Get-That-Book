package com.wicgames.window;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.wicgames.game.Main;
import com.wicgames.gameObjects.GameObject;
import com.wicgames.physics.Body;
import com.wicgames.physics.CollisionDetection;
import com.wicgames.physics.CollisionResolution;
import com.wicgames.physics.Manifold;
import com.wicgames.wicLibrary.Animation;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Vector2;

public abstract class Scene {
	public static Scene currentScene;		//Holds the current scene, set this to a new scene when wanting to change the scene
	public Vector2 camera = new Vector2();	//The offset to draw things so the character is always drawn in the middle of the screen
	public Vector2 size = new Vector2();	//The TileSize of the current Scene, used to help determine the offset of the camera
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();	//Holds all the game objects
	public ArrayList<Drawable> drawables = new ArrayList<Drawable>();	//Holds everything in the scene that can be drawn
	public ArrayList<Body> bodies = new ArrayList<Body>();				//Holds all the bodies in the scene
	
	public Scene() {
		// Constructor for the scene, calls the  destroy method on the last scene when creating a new scene
		if (Scene.currentScene != null)
			Scene.currentScene.destroy();
	}
	public void update(double delta) {
		//Basic update method for physics
		Manifold.clearManifolds();
		Body.update(bodies, delta);
		Animation.updateAll(delta);
		//Constraint.update(constraints);

		CollisionDetection.BroadPhase(bodies);
		CollisionResolution.update(Manifold.all);
	}

	public void draw(Graphics2D graphics2D) {
		//Draws everything in the current scene that can be drawn
		Drawable.draw(graphics2D, drawables);
	}
	public abstract void init(); //Initializes the scene, this is where we also initialize most of the variables in the scene
	public void destroy() {
		//Clears the JPanel from its buttons so there not drawn after we go to the next scene
		Main.panel.removeAll();
	}
}
