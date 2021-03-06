package com.wicgames.wicLibrary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	public static ArrayList<Animation> all = new ArrayList<Animation>();
	public Event finished = new Event();
	private SpriteSheet animations;
	private Drawable animated;
	private double frameDelay;
	private double currentTime;
	private int currentIndex;
	private int startIndex;
	private int endIndex;
	private int loopNumber;
	private int fullLoopNumber;
	private boolean running = false;
	private Image stopImage;
	/**
	 * 
	 * @param source
	 *            Sprite Sheet for animation
	 * @param startIndex
	 *            First Image in animation
	 * @param nSprites
	 *            Number of images in animation after first image(# of images =
	 *            this + 1)
	 * @param texture
	 *            Drawable that is being animated
	 * @param frameDelay
	 *            Delay between frames (in seconds?)
	 * @param loopNumber
	 *            Number of time animation is looped through fully (-1 to loop
	 *            forever)
	 */
	public Animation(SpriteSheet source, int startIndex, int nSprites,
			Drawable texture, double frameDelay, int loopNumber, Image stopImage) {
		animations = source;
		this.startIndex = startIndex;
		this.endIndex = startIndex + nSprites - 1;
		this.frameDelay = frameDelay;
		this.animated = texture;
		fullLoopNumber = loopNumber;
		this.currentTime = 0;
		this.currentIndex = startIndex;
		this.stopImage = stopImage;
		all.add(this);
	}
	public void update(double delta) {
		if (running) {
			currentTime += delta;
			if (currentTime > frameDelay) {
				currentTime = 0;
				nextImage();
				currentIndex++;
			}
		}
	}

	public void nextImage() {
		BufferedImage nextSprite = (BufferedImage) animations
				.getImage(currentIndex);
		animated.updateImage(nextSprite);
		// for (int x = 0; x < nextSprite.getWidth(null); x++)
		// for (int y = 0; y < nextSprite.getHeight(null); y++) {
		// ((BufferedImage)texture).setRGB(x, y, nextSprite.getRGB(x,y));
		// }
		if (currentIndex > endIndex) {
			currentIndex = startIndex - 1;
			loopNumber--;
			if (loopNumber == 0) {
				//Done animation
				finished.trigger();
				stop();
			}
		}
	}

	public boolean isRunning() {
		return running;
	}
	public void destroy() {
		all.remove(this);
	}

	public static void updateAll(double delta) {
		for (int i = 0; i < all.size(); i++) {
			all.get(i).update(delta);
		}
	}

	public void start() {
		running = true;
		loopNumber = fullLoopNumber;
	}

	public void pause() {
		running = false;
	}

	public void stop() {
		running = false;
		animated.updateImage(stopImage);
	}
}
