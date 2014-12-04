package com.wicgames.gameObjects;

import com.wicgames.physics.Body;

import java.awt.*;

public class Platform extends GameObject{
    public Platform(int x, int y, int width, int height){
        body = new Body(x*tileSize,y*tileSize, width*tileSize,height*tileSize);
    }    
    public void draw(Graphics g){
        g.fillRect((int)body.position.x,(int)body.position.y,(int)body.size.x, (int) body.size.y);
    }
}
