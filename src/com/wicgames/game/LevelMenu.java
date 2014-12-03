package com.wicgames.game;

import com.wicgames.scene.Scene;
import com.wicgames.wicLibrary.Drawable;
import com.wicgames.wicLibrary.SpriteSheet;
import com.wicgames.wicLibrary.Vector2;
import com.wicgames.window.Button;

public class LevelMenu extends Scene{
	
	private Button[][] levelButtons;
	private SpriteSheet buttons;
	
	public LevelMenu() {
		super();
		Main.panel.setLayout(null);
		levelButtons = new Button[2][1];
		buttons = new SpriteSheet("bin/assets/textures/ButtonSprites.png", 16, 16, 1);
	}
	
	@Override
	public void init() {
		int levelNum = 0;
		for(int x = 0;x < levelButtons.length;x++){
			for(int y = 0;y < levelButtons[x].length;y++){
				levelNum++;
				if(levelNum <= Integer.parseInt(Data.save.getValue("curLevel")))
					levelButtons[x][y] = new Button(buttons.getImage(1 + ((levelNum - 1)* 2), 0),new Vector2(100 + x * 32,100 +y * 32));
				else
					levelButtons[x][y] = new Button(buttons.getImage(((levelNum - 1)* 2), 0),new Vector2(100 + x * 32,100 +y * 32));
				Main.panel.add(levelButtons[x][y]);
				Scene.currentScene.drawables.add((Drawable) levelButtons[x][y]);
			}
		}
		Main.frame.pack();
	}

}
