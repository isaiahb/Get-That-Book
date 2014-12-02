package com.wicgames.wicLibrary;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public interface ClickListener {
	static ArrayList<ClickListener> clicks = new ArrayList<ClickListener>();
	static void click(MouseEvent e){
		for(ClickListener click : clicks){
			click.clicked(e);
		}
	}
	void clicked(MouseEvent e);
}
