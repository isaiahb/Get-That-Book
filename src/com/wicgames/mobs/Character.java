package com.wicgames.mobs;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.physics.Body;

public class Character extends Mob {
    public Character(){
        health = 100;
        armour = 10;
        damageBoost = 2;
        try {
            texture = ImageIO.read(new File("bin/assets/textures/character.png"));
        } catch (IOException e) {
            System.out.println("character texture failed");
        }
        body = new Body(50,50,32,64);
        body.acceleration.add(0,20);
        body.unAnchore();
        created(this);
    }
}
