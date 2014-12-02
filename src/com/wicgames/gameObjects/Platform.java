package com.wicgames.gameObjects;

import com.wicgames.physics.Body;

import java.awt.*;

public class Platform {
    Body body;
    public Platform(int x, int y, int width, int height){
        body = new Body(x,y,width,height);
    }
    public void draw(Graphics g){
        g.fillRect((int)body.position.x,(int)body.position.y,(int)body.size.x, (int) body.size.y);
    }
}
