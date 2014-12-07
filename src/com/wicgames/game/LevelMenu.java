package com.wicgames.game;

import java.awt.event.KeyEvent;

import com.wicgames.input.Key;
import com.wicgames.wicLibrary.Animation;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.Function;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Button;
import com.wicgames.window.Scene;

public class LevelMenu extends Scene{
	Function goBack;
	private Button[][] levelButtons;
	private SpriteSheet buttons;
	private Vector2 sheetSize;
	public LevelMenu() {
		super();
		Main.panel.setLayout(null);
		buttons = new SpriteSheet("bin/assets/textures/ButtonSprites.png", 16, 16, 1,0);
		sheetSize = buttons.getNumSprites();
		levelButtons = new Button[(int) (sheetSize.x / 2)][(int) sheetSize.y];
	}
	
	@Override
	public void init() {
		int levelNum = 0;
		for(int x = 0;x < levelButtons.length;x++) {
			for(int y = 0;y < levelButtons[x].length;y++) {
				levelNum++;
				final int buttonLevel = levelNum;
				if(levelNum <= Integer.parseInt(Data.save.getValue("currentLevel"))){
					levelButtons[x][y] = new Button(buttons.getImage(1 + ((levelNum - 1)* 2)),100 + x * 32,100 +y * 32);
					levelButtons[x][y].addActionListener((e) ->{
						currentScene = new LevelScene(buttonLevel);
						currentScene.init();  
					});
				}//Gets button regular button image
				else{
					levelButtons[x][y] = new Button(buttons.getImage(((levelNum - 1)* 2)), 100 + x * 32, 100 + y * 32); //Gets grayed out button image
					levelButtons[x][y].setEnabled(false);
				}
				Main.panel.add(levelButtons[x][y]);
				Scene.currentScene.drawables.add((Drawable) levelButtons[x][y]);
			}
		}
		goBack = new Function(){
			@Override
			public void call() {
				currentScene = new Menu();
				currentScene.init();
			}
		};
		
		Key.released[KeyEvent.VK_BACK_SPACE].connect(goBack);
		Main.frame.pack();
	}
	@Override
	public void destroy() {
		super.destroy();
		Key.released[KeyEvent.VK_BACK_SPACE].disconnect(goBack);
	}
}
