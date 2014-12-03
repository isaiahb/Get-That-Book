package com.wicgames.gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import com.wicgames.physics.Body;

public abstract class GameObject {
	public Body body;
	public Image texture;
	public static void created(GameObject object){}
	public static void draw(Graphics2D graphics2D, ArrayList<GameObject> objects){}
	public void draw(Graphics2D graphics2D){};
	
}
