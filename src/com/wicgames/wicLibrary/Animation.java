package com.wicgames.wicLibrary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	public static ArrayList<Animation> all = new ArrayList<Animation>();
	private SpriteSheet animations;
	private Drawable animated;
	private int frameDelay;
	private double currentTime;
	private int currentIndex;
	private int startIndex;
	private int endIndex;
	private int loopNumber;
	/**
	 * 
	 * @param source Sprite Sheet for animation
	 * @param startIndex First Image in animation
	 * @param nSprites Number of images in animation after first image(# of images = this + 1)
	 * @param texture Drawable that is being animated
	 * @param frameDelay Delay between frames (in seconds?)
	 * @param loopNumber Number of time animation is looped through fully (-1 to loop forever)
	 */
	public Animation(SpriteSheet source, int startIndex, int nSprites,
			Drawable texture, int frameDelay, int loopNumber) {
		animations = source;
		this.startIndex = startIndex;
		this.endIndex = startIndex + nSprites - 1;
		this.frameDelay = frameDelay;
		this.animated = texture;
		this.loopNumber = loopNumber;
		this.currentTime = 0;
		this.currentIndex = startIndex;
		all.add(this);
	}

	public void update(double delta) {
		currentTime += delta;
		if (currentTime > frameDelay) {
			System.out.println("Next Image");
			currentTime = 0;
			nextImage();
			currentIndex++;
		}
	}

	public void nextImage() {
		System.out.println(currentIndex);
		BufferedImage nextSprite = (BufferedImage) animations
				.getImage(currentIndex);
		animated.updateImage(nextSprite);
//		for (int x = 0; x < nextSprite.getWidth(null); x++)
//			for (int y = 0; y < nextSprite.getHeight(null); y++) {
//				((BufferedImage)texture).setRGB(x, y, nextSprite.getRGB(x,y));
//			}
		if (currentIndex > endIndex) {
			currentIndex = startIndex - 1;
			loopNumber--;
			if (loopNumber == 0) {
				destroy();
			}
		}
	}

	public void destroy() {
		all.remove(this);
	}

	public static void updateAll(double delta) {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).update(delta);
		}
	}
}
