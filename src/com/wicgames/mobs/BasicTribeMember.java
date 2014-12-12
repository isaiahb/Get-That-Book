package com.wicgames.mobs;

import com.sun.javafx.Utils;
import com.wicgames.game.LevelScene;
import com.wicgames.physics.Force;
import com.wicgames.physics.Material;
import com.wicgames.physics.Rectangle;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Scene;
public class BasicTribeMember extends Mob{
	private static Force moveLeft = new Force.Gravity(-400, 0);
	private static Force moveRight = new Force.Gravity(400, 0);
	private int attackRadius;

	public BasicTribeMember(int x,int y) {
        health = 20;
        armour = 0;
        damageBoost = 1;
        body = new Rectangle(x,y,32,64){
        	@Override
        	public void update(double delta) {
        		super.update(delta);
				velocity.x = Math.copySign(Math.max(Math.abs(velocity.x) -10,0), velocity.x);
				velocity.x = Utils.clamp(-100,velocity.x,100);
        		BasicTribeMember.this.update(delta);
        	}
        };
        attackRadius = (int)(64 + body.size.magnitude());
        created(this);
        body.setMaterial(Material.Flesh);
    }
	public void update(double delta){
		if(((LevelScene)Scene.currentScene).player != null){
			Rectangle characterHitbox = (Rectangle) ((LevelScene)Scene.currentScene).player.body;
			double closestX = Utils.clamp(body.position.x, characterHitbox.position.x, body.position.x + body.size.x);
			double closestY = Utils.clamp(body.position.y, characterHitbox.position.y, body.position.y + body.size.y);
			Vector2 closest = new Vector2(closestX,closestY);
			if(Vector2.sub(closest,characterHitbox.position).magnitudeSquared() < attackRadius * attackRadius){
				if(characterHitbox.position.x + characterHitbox.size.x > body.position.x){
					moveRight.bodies.add(body);
					moveLeft.bodies.remove(body);
				}
				if(characterHitbox.position.x + characterHitbox.size.x < body.position.x){
					moveLeft.bodies.add(body);
					moveRight.bodies.remove(body);
				}		
			}
		}
	}
}
