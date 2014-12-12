package com.wicgames.game;

public class LightSource{
	public int x,y;
	public short lightLevel;
	public LightSource(int x,int y,int lightLevel){
		this.x = x;
		this.y = y;
		this.lightLevel = (short) lightLevel;
	}
}
