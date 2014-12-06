package com.wicgames.mobs;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.input.Key;
import com.wicgames.physics.Body;
import com.wicgames.wicLibrary.Function;

public class Character extends Mob {

    public Character(){
        health = 100;
        armour = 10;
        damageBoost = 2;
        moveSpeed = 0.4;
        maxSpeed = 4;
        slowSpeed = 0.1;
        gravitySpeed = 0.3;
        jumpThreshold = 0.032;
        Key.pressed[KeyEvent.VK_D].connect(new Function(){
        	public void call(){
        		right = true;
        	}
        });
        Key.pressed[KeyEvent.VK_A].connect(new Function(){
        	public void call(){
        		left = true;
        	}
        });
        Key.pressed[KeyEvent.VK_W].connect(new Function(){
        	public void call(){
        		jumping = true;
        		jumpCall = 0;
        	}
        });
        Key.released[KeyEvent.VK_D].connect(new Function(){
        	public void call(){
        		right = false;
        	}
        });
        Key.released[KeyEvent.VK_A].connect(new Function(){
        	public void call(){
        		left = false;
        	}
        });
        Key.released[KeyEvent.VK_W].connect(new Function(){
        	public void call(){
        		jumping = false;
        	}
        });
        try {
            texture = ImageIO.read(new File("bin/assets/textures/character.png"));
        } catch (IOException e) {
            System.out.println("character texture failed");
        }
        body = new Body(50,50,32,64){
        	@Override
        	public void update(double delta){
        		System.out.println(position);
        		jumpCall += delta;
        		velocity.y += gravitySpeed;
        		if(jumpCall > jumpThreshold)jumping = false;
        		if(right)velocity.x = Math.min(velocity.x + moveSpeed, maxSpeed);
        		if(left)velocity.x = Math.max(velocity.x - moveSpeed, -maxSpeed);
        		if(jumping && onTop())velocity.y -= 5;
        		velocity.x = Math.copySign(Math.max(Math.abs(velocity.x) - slowSpeed,0), velocity.x);
        		velocity.y = Math.copySign(Math.max(Math.abs(velocity.y) - slowSpeed,0), velocity.y);
        		position.add(velocity);
        	}
        };
        body.unAnchore();
        created(this);
    }
    public void update(double delta){
    	
    }
}
