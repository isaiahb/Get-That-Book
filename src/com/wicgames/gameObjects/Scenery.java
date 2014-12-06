package com.wicgames.gameObjects;

import com.wicgames.physics.Rectangle;

import java.awt.*;

public class Scenery extends GameObject{
    public Scenery(int x, int y, int width, int height,Image texture){
        super.texture = texture;
        body = new Rectangle(x*tileSize,y*tileSize, width*tileSize,height*tileSize);
        body.canCollide = false;
        created(this);
    }
}
