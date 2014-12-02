package com.wicgames.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener{
    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if(key == ('w')){}
        if(key == ('a')){}
        if(key == ('s')){}
        if(key == ('d')){}
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
