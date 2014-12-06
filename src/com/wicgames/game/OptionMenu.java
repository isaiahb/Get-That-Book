package com.wicgames.game;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import com.wicgames.input.Key;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Function;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class OptionMenu extends Scene{
	@Override
	public void init() {
		String[] res = {"1024x576","512x288","256x144"};
		JComboBox<String> resolutions = new JComboBox<String>();
		Button save = new Button("bin/assets/textures/SaveButton.png",(int)(Main.WIDTH * 0.75),(int)(Main.HEIGHT * 0.75));
		save.addActionListener((e) ->{
			
		});
		Main.panel.add(save);
		currentScene.drawables.add((Drawable) save);
		Key.released[KeyEvent.VK_BACK_SPACE].connect(new Function(){
			@Override
			public void call() {
				currentScene = new Menu();
				currentScene.init();
			}
		});
	}

}
