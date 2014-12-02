package com.wicgames.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.wicgames.wicLibrary.Vector2;

public class Mouse implements MouseListener {
	public static boolean Button1Down = false;
	public static boolean Button2Down = false;
	public static Vector2 position = new Vector2();
	
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
}
