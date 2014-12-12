package com.wicgames.game;

public class LightSource{
	public int x,y;
	public short intensity;
	
	public LightSource(int x,int y,int intensity){
		this.x = x;
		this.y = y;
		this.intensity = (short) intensity;
	}
}
