package com.wicgames.mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.javafx.Utils;
import com.wicgames.game.Data;
import com.wicgames.game.Main;
import com.wicgames.input.Key;
import com.wicgames.physics.Body;
import com.wicgames.physics.Force;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.Animation;
import com.wicgames.wicLibrary.Function;
import com.wicgames.wicLibrary.ImageMethods;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Character extends Mob {
	public static SpriteSheet walkingSheet, idleSheet, fallingSheet, jumpingSheet;
	private Force moveRight = new Force.Gravity(5000, 0);
	private Force moveLeft = new Force.Gravity(-5000, 0);
	private Animation walking, idling, falling, jumping;
	public Character() {
		if(walkingSheet == null)
			walkingSheet = new SpriteSheet("bin/assets/textures/CharacterWalking.png",22,64,2,1);
		if(idleSheet == null)
			idleSheet = new SpriteSheet("bin/assets/textures/CharacterIdol.png",22,64,2,1);
		if(fallingSheet == null)
			fallingSheet = new SpriteSheet("bin/assets/textures/FallingSheet.png",22,64,2,1);
		falling = new Animation(fallingSheet,0,1,this,0.1,-1,idleSheet.getImage(0));
		walking = new Animation(walkingSheet,0,5,this,0.1,-1,idleSheet.getImage(0));
		idling = new Animation(idleSheet,0,9,this,0.1,-1,idleSheet.getImage(0));
		idling.start();
		health = 100;
		armour = 10;
		damageBoost = 2;
		jumpThreshold = 0.1;
		Key.pressed[Integer.parseInt(Data.config.getValue("Move Right"))].connect(new Function() {
			public void call() {
				right = true;
				if(!moveRight.bodies.contains(body))
					moveRight.add(body);
			}
		});
		Key.pressed[Integer.parseInt(Data.config.getValue("Move Left"))].connect(new Function() {
			public void call() {
				left = true;
				if(!moveLeft.bodies.contains(body))
					moveLeft.add(body);
			}
		});
		Key.released[Integer.parseInt(Data.config.getValue("Move Right"))].connect(new Function() {
			public void call() {
				right = false;
				moveRight.remove(body);
			}
		});
		Key.released[Integer.parseInt(Data.config.getValue("Move Left"))].connect(new Function() {
			public void call() {
				left = false;
				moveLeft.remove(body);
			}
		});
		Key.pressed[Integer.parseInt(Data.config.getValue("Jump"))].connect(new Function() {
			public void call() {
				jumpRequest = true;
				jumpCall = 0;
			}
		});
		texture = walkingSheet.getImage(0);
		body = new Rectangle(50, 50, texture.getWidth(null), texture.getHeight(null)) {
			@Override
			public void update(double delta) {
				super.update(delta);
				velocity.x = Math.copySign(Math.max(Math.abs(velocity.x) - 8,0), velocity.x);
				velocity.x = Utils.clamp(-100,velocity.x,100);
				Vector2 mid = Main.HALF;
				Vector2 offset = Vector2.sub(position, mid);
				Vector2 sceneSize = Scene.currentScene.size;
				offset.x = position.x > mid.x ? offset.x : 0;
				offset.x = position.x < sceneSize.x - mid.x ? offset.x :  sceneSize.x - mid.x * 2;

				offset.y = position.y > mid.y ? offset.y : 0;
				offset.y = position.y < Scene.currentScene.size.y - mid.y ? offset.y : sceneSize.y - mid.y * 2;
				Scene.currentScene.camera.setTo(offset);
				Character.this.update(delta);
			}
		};
		body.setMaterial(Material.Flesh);
		created(this);
	}
	@Override
	public void draw(Graphics2D graphics2d) {
		Vector2 mid = Main.HALF;
		Vector2 offset = Vector2.sub(body.position, mid);
		Vector2 sceneSize = Scene.currentScene.size;
		
		offset.x = body.position.x > mid.x ? offset.x : 0;
		offset.x = body.position.x < sceneSize.x - mid.x ? offset.x :  sceneSize.x - mid.x * 2;

		offset.y = body.position.y > mid.y ? offset.y : 0;
		offset.y = body.position.y < sceneSize.y - mid.y ? offset.y : sceneSize.y - mid.y * 2;
		Vector2 p = Vector2.sub(body.position, offset);
		BufferedImage flipTexture = ImageMethods.copyImage((BufferedImage)texture);
		if(left)
			ImageMethods.flipImage((BufferedImage) flipTexture);
		if (flipTexture != null)
			graphics2d.drawImage(flipTexture,(int)p.x,(int)p.y,null);
		else{
			graphics2d.setColor(Color.PINK);
	        graphics2d.fillRect((int)p.x,(int)p.y,(int)p.x, (int) p.y);
	        graphics2d.setColor(Color.BLACK);
	        graphics2d.drawRect((int)p.x,(int)p.y,(int)p.x, (int) p.y);
		}
	}
	public void update(double delta) {
		jumpCall += delta;
		if(jumpCall > jumpThreshold){//Jumprequest expired
			jumpRequest = false;
		}
		if(jumpRequest && onTopAny()){
			body.velocity.y += -400;
			jumpRequest = false;
		}
		jumpThreshold = delta * 20;
		//Animation
		if(body.velocity.y > 30){
			if(!falling.isRunning())
				falling.start();
			if(idling.isRunning())
				idling.stop();
			if(walking.isRunning())
				walking.stop();
		}
		else if(left || right){
			if(!walking.isRunning())
				walking.start();
			if(idling.isRunning())
				idling.stop();
			if(falling.isRunning())
				falling.stop();
		}else{
			if(!idling.isRunning())
				idling.start();
			if(walking.isRunning())
				walking.stop();
			if(falling.isRunning())
				falling.stop();
		}
	}
	public boolean onTopAny(){
		for(Body b : body.touching)
			if(body.onTop(b) && !body.besideOf(b))
				return true;
		return false;
	}
}
