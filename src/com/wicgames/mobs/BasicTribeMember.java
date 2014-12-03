package com.wicgames.mobs;

import com.wicgames.wicLibrary.Drawable;

import java.awt.*;

public class BasicTribeMember extends BasicMob implements Drawable{
    public BasicTribeMember() {
        health = 20;
        armour = 0;
        damageBoost = 1;
    }

    @Override
    public void draw(Graphics g) {

    }
}
