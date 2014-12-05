package com.wicgames.game;

import javax.swing.JComboBox;

import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class OptionMenu extends Scene{
	@Override
	public void init() {
		JComboBox<String> resolutions = new JComboBox<String>();
		Button save = new Button("bin/assets/textures/SaveButton.png",(int)(Main.WIDTH * 0.75),(int)(Main.HEIGHT * 0.75));
		save.addActionListener((e) ->{
			
		});
	}
	
}
