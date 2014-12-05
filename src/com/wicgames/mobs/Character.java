package com.wicgames.mobs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Character extends Mob {
    public Character(){
        health = 100;
        armour = 10;
        damageBoost = 2;
        /*try {
            texture = ImageIO.read(new File("bin/assets/textures/character.png"));
        } catch (IOException e) {
            System.out.println("character texture failed");
        }*/
    }
}
