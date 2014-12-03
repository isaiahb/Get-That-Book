package com.wicgames.gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import com.wicgames.physics.Body;
import com.wicgames.wicLibrary.Drawable;

public abstract class GameObject implements Drawable{
	public Body body;
	public Image texture;
	public static void created(GameObject object){}
	
	public void draw(Graphics2D graphics2D){};
	
}
