package com.wicgames.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Event;
import com.wicgames.wicLibrary.Vector2;

public class Mouse {
	public static class button1 {
		public static boolean down = false;
		public static Event pressed = new Event();
		public static Event released = new Event();
	}
	public static class button2 {
		public static boolean down = false;
		public static Event pressed = new Event();
		public static Event released = new Event();
	}
	public static class button3 {
		public static boolean down = false;
		public static Event pressed = new Event();
		public static Event released = new Event();
	}
	public static Vector2 position = new Vector2();
	public static Event moved = new Event();
	public static Event dragged = new Event();
	public static Event wheeled = new Event();
	public static void init() {
		Main.panel.addMouseListener(listener);
		Main.panel.addMouseMotionListener(motionAdapter);
		Main.panel.addMouseWheelListener(wheelListener);
	}
	
	public static MouseMotionAdapter motionAdapter = new MouseMotionAdapter(){
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			dragged.trigger(x, y, x - position.x, y - position.y);
			dragged.trigger(x, y);
			position.x = x;
			position.y = y;
			
		}
		
		
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			moved.trigger(x, y, x - position.x, y - position.y);
			moved.trigger(x, y);
			position.x = x;
			position.y = y;
			
		}
	};
	public static MouseWheelListener wheelListener = new MouseWheelListener(){

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			wheeled.trigger(e.getWheelRotation());
		}
		
	};
	public static MouseAdapter listener = new MouseAdapter(){	  
	    //Pressed
	    public void mousePressed(MouseEvent e) {
	    	// Left 
	    	if(SwingUtilities.isLeftMouseButton(e)) {
	    		button1.pressed.trigger();	  
	    		button1.pressed.trigger(e);
	    		button1.down = true;
	    	}
	    	// Right
	    	if(SwingUtilities.isRightMouseButton(e)) {	    		
	    		button2.pressed.trigger();
	    		button2.pressed.trigger(e);
	    		button2.down = true;
	    	}
	    	//Middle
	    	if(SwingUtilities.isMiddleMouseButton(e)) {
	    		button3.pressed.trigger();
	    		button3.down = true;
	    	}
	    }

	    //Released
	    public void mouseReleased(MouseEvent e)  {
	    	// Left 
	    	if(SwingUtilities.isLeftMouseButton(e)) {
	    		button1.released.trigger();	    	
	    		button1.down = false;
	    	}
	    	// Right
	    	if(SwingUtilities.isRightMouseButton(e)) {
	    		button2.released.trigger();
	    		button2.down = false;
	    	}
	    	//Middle
	    	if(SwingUtilities.isMiddleMouseButton(e)) {
	    		button3.released.trigger();
	    		button3.down = false;
	    	}
	    }
	};
}
