package com.wicgames.mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.javafx.Utils;
import com.wicgames.game.Main;
import com.wicgames.input.Key;
import com.wicgames.physics.Force;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.Function;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;

public class Character extends Mob {
	private Force moveLeft = new Force.Gravity(-200, 0);
	private Force moveRight = new Force.Gravity(200, 0);

	public Character() {
		health = 100;
		armour = 10;
		damageBoost = 2;
		moveSpeed = 0.4;
		maxSpeed = 4;
		slowSpeed = 0.1;
		gravitySpeed = 0.3;
		jumpThreshold = 0.032;
		Key.pressed[KeyEvent.VK_D].connect(new Function() {
			public void call() {
				right = true;
				if (!moveRight.bodies.contains(Character.this.body))
					moveRight.add(Character.this.body);
			}
		});
		Key.pressed[KeyEvent.VK_A].connect(new Function() {
			public void call() {
				left = true;
				if (!moveLeft.bodies.contains(Character.this.body))
					moveLeft.add(Character.this.body);
			}
		});
		Key.pressed[KeyEvent.VK_W].connect(new Function() {
			public void call() {
				jumping = true;
				jumpCall = 0;
			}
		});
		Key.released[KeyEvent.VK_D].connect(new Function() {
			public void call() {
				right = false;
				moveRight.remove(Character.this.body);
			}
		});
		Key.released[KeyEvent.VK_A].connect(new Function() {
			public void call() {
				left = false;
				moveLeft.remove(Character.this.body);
			}
		});
		Key.released[KeyEvent.VK_W].connect(new Function() {
			public void call() {
				jumping = false;
			}
		});
		try {
			texture = ImageIO
					.read(new File("bin/assets/textures/character.png"));
		} catch (IOException e) {
			System.out.println("character texture failed");
		}
		body = new Rectangle(50, 50, 32, 64) {
			@Override
			public void update(double delta) {
				super.update(delta);
				System.out.println(velocity.y);
				velocity.x = Math.copySign(Math.abs(velocity.x) - 4, velocity.x);
				velocity.y = Math.copySign(Math.abs(velocity.y) - 4, velocity.y);
				velocity.x = Utils.clamp(-1000, velocity.x, 1000);
				velocity.y = Utils.clamp(-1000, velocity.y, 1000);
				System.out.println(velocity.y);
				Vector2 mid = Main.HALF;
				Vector2 offset = Vector2.sub(position, mid);
				Vector2 sceneSize = Scene.currentScene.size;
				offset.x = position.x > mid.x ? offset.x : 0;
				offset.x = position.x < sceneSize.x - mid.x ? offset.x :  sceneSize.x - mid.x * 2;

				offset.y = position.y > mid.y ? offset.y : 0;
				offset.y = position.y < Scene.currentScene.size.y - mid.y ? offset.y : sceneSize.y - mid.y * 2;
				Scene.currentScene.camera.setTo(offset);
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
		
		if (texture != null)
			graphics2d.drawImage(texture,(int)p.x,(int)p.y,null);
		else{
			graphics2d.setColor(Color.PINK);
	        graphics2d.fillRect((int)p.x,(int)p.y,(int)p.x, (int) p.y);
	        graphics2d.setColor(Color.BLACK);
	        graphics2d.drawRect((int)p.x,(int)p.y,(int)p.x, (int) p.y);
		}
	}
	public void update(double delta) {
		
	}
}
