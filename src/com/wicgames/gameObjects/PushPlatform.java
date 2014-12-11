package com.wicgames.gameObjects;

import java.awt.Image;

import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;

public class PushPlatform extends GameObject {
	public PushPlatform(int x, int y, int width, int height, Image texture) {
		super.texture = texture;
		body = new Rectangle(x, y, width,
				height);
		body.setMaterial(Material.Wood);
		body.affectedGravity = false;
		created(this);
		
	}
}
