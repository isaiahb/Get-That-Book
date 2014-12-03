package com.wicgames.game;

import javax.swing.JButton;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Button;

public class LevelMenu extends Scene{
	private Button[][] levelButtons;
	private SpriteSheet buttons;
	public LevelMenu() {
		Main.panel.setLayout(null);
		levelButtons = new Button[1][3];
		buttons = new SpriteSheet("", 16, 16, 1);
	}
	
	@Override
	public void init() {
		int levelNum = 0;
		for(int y = 0;y < levelButtons.length;y++){
			for(int x = 0;x < levelButtons[y].length;x++){
				levelNum++;
				levelButtons[x][y] = new Button(buttons.getImage(1 + ((levelNum - 1)* 2), 0),new Vector2(x * 16,y * 16));
			}
		}
	}

}
