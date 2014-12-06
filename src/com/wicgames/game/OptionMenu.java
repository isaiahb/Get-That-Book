package com.wicgames.game;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import com.wicgames.input.Key;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Function;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class OptionMenu extends Scene{
	Function goBack;
	@Override
	public void init() {
		String[] res = {"1024x576","512x288","256x144"};
		JComboBox<String> resolutions = new JComboBox<String>(res);
		resolutions.setBounds((int)(Main.WIDTH * 0.1 * Main.scale),(int)(Main.HEIGHT * 0.1 * Main.scale),(int)(100 * Main.scale),(int)(50 * Main.scale));
		Button save = new Button("bin/assets/textures/SaveButton.png",(int)(Main.WIDTH * 0.75),(int)(Main.HEIGHT * 0.75));
		save.addActionListener((e) ->{
			Data.config.setValue("scale", String.valueOf(Integer.parseInt(((String)resolutions.getSelectedItem()).split("x")[0]) / (double)Main.WIDTH));
			System.out.println(Data.config.getValue("scale"));
		});
		resolutions.setFocusable(false);
		Main.panel.add(save);
		Main.panel.add(resolutions);
		currentScene.drawables.add((Drawable) save);
		goBack = new Function(){
			@Override
			public void call() {
				currentScene = new Menu();
				currentScene.init();
			}
		};
		
		Key.released[KeyEvent.VK_BACK_SPACE].connect(goBack);
	}
	@Override
	public void destroy() {
		super.destroy();
		Key.released[KeyEvent.VK_BACK_SPACE].disconnect(goBack);
	}
}
