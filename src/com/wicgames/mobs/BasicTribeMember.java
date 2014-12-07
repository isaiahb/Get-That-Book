package com.wicgames.mobs;

import com.sun.javafx.Utils;
import com.wicgames.physics.Force;
import com.wicgames.physics.Rectangle;

public class BasicTribeMember extends Mob {
	private Force moveLeft = new Force.Gravity(-400, 0);
	private Force moveRight = new Force.Gravity(400, 0);

	public BasicTribeMember(int x,int y) {
        health = 20;
        armour = 0;
        damageBoost = 1;
        body = new Rectangle(x,y,32,64) {
			@Override
			public void update(double delta) {
				super.update(delta);
				System.out.println(velocity.y);
				velocity.x = Math.copySign(Math.max(Math.abs(velocity.x) - 8,0), velocity.x);
				velocity.y = Math.copySign(Math.max(Math.abs(velocity.y) - 8,0), velocity.y);
				velocity.x = Utils.clamp(-1000, velocity.x, 1000);
				velocity.y = Utils.clamp(-1000, velocity.y, 1000);
			}
        };
    }
}
