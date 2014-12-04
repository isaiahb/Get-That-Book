package com.wicgames.wicLibrary;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface Drawable {
	//Interface for objects that need to be drawn
	void draw(Graphics2D graphics2D);
	/**
	 *  Draws all objects in array
	 */
	static void draw(Graphics2D graphics2D, ArrayList<Drawable> objects){
		for(Drawable draw : objects){
			draw.draw(graphics2D);
		}
	}
}
