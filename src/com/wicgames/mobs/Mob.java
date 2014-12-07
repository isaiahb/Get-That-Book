package com.wicgames.mobs;

import com.wicgames.gameObjects.GameObject;

import java.awt.*;

public abstract class Mob extends GameObject{
	//Mob stuff
    public int health;
    public int armour;
    public int damageBoost;
    
    //Booleans for animation and stuff
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumpRequest;
    protected boolean falling;
    
	protected double jumpCall;
	protected double jumpThreshold;
    protected double moveSpeed;
    protected double maxSpeed;
    protected double slowSpeed;
    protected double gravitySpeed = 6;
    
}
