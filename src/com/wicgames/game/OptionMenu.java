package com.wicgames.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.wicgames.input.Key;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Function;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class OptionMenu extends Scene{
	private Function goBack;
	private String[] keyBindings = {"Move Left","Move Right","Jump"};
	private Button[] keyBindingButtons = new Button[keyBindings.length];
	private int[] keyValues = new int[keyBindings.length];
	private int selectedKeyBinding = -1;
	@Override
	public void init() {
		String[] res = {"3840x2160","2560x1440","1920x1080","1280x720","960x540","640x360","480x270"};
		String currentResolution = String.valueOf(Main.panel.getWidth()) + "x" + String.valueOf(Main.panel.getHeight()); 
		JComboBox<String> resolutions = new JComboBox<String>(res);
		for(String resol : res)
			if(currentResolution.equals(resol))
				resolutions.setSelectedItem(resol);
		resolutions.setBounds((int)(Main.WIDTH * 0.1 * Main.scale),(int)(Main.HEIGHT * 0.1 * Main.scale),(int)(100 * Main.scale),(int)(50 * Main.scale));
		Button save = new Button("bin/assets/textures/SaveButton.png",(int)(Main.WIDTH * 0.75),(int)(Main.HEIGHT * 0.75));
		save.addActionListener((e) ->{
			Data.config.setValue("scale", String.valueOf(Integer.parseInt(((String)resolutions.getSelectedItem()).split("x")[0]) / (double)Main.WIDTH));
			Main.frame.dispose();
			Main.init();
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
		for(int i = 0;i < keyBindings.length;i++){
			String binding = keyBindings[i];
			JLabel keyName = new JLabel(binding);
			keyName.setBounds(50,i * 35 + 200,100,25);
			final int fI = i;
			keyValues[i] = Integer.parseInt(Data.config.getValue(binding));
			keyBindingButtons[i] = new Button("bin/assets/textures/KeyBinding.png",150,i * 35 + 215){
				int index = fI;
				@Override
				public void draw(Graphics2D g) {
					char print = (char)keyValues[index];
					super.draw(g);
					g.setColor(Color.BLACK);
					g.drawString(String.valueOf(print),(int)position.x + 20,(int)position.y + 20);
				}
			};
			keyBindingButtons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedKeyBinding = fI;
				}
			});
			Main.panel.add(keyName);
			Main.panel.add(keyBindingButtons[i]);
			currentScene.drawables.add(keyBindingButtons[i]);
		}
		Key.released[KeyEvent.VK_BACK_SPACE].connect(goBack);
		Key.typedEvent.connect(new Function(){
			@Override
			public <T> void call(T a) {
				if(selectedKeyBinding != -1){
					System.out.println(((KeyEvent)a).getKeyCode());
					keyValues[selectedKeyBinding] = ((KeyEvent)a).getKeyCode();
					Data.config.setValue(keyBindings[selectedKeyBinding], String.valueOf(keyValues[selectedKeyBinding]));
				}
			}
		});
	}
	@Override
	public void destroy() {
		super.destroy();
		Key.released[KeyEvent.VK_BACK_SPACE].disconnect(goBack);
	}
	
}
