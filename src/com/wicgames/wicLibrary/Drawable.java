package com.wicgames.wicLibrary;

import java.awt.Graphics2D;
import java.util.ArrayList;

public interface Drawable {
	void draw(Graphics2D graphics2D);
	static void draw(Graphics2D graphics2D, ArrayList<Drawable> objects){
		for(Drawable draw : objects){
			draw.draw(graphics2D);
		}
	}
}
