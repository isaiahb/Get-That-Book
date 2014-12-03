package com.wicgames.wicLibrary;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wicgames.game.Main;

public class SpriteSheet {
	private Vector2 spriteSize;
	private Vector2 numSprites;
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	public SpriteSheet(String path,int x,int y){
		spriteSize = new Vector2(x,y);
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		numSprites = new Vector2(sheet.getWidth(null) / spriteSize.x, sheet.getHeight(null) / spriteSize.y);
		sprites = new BufferedImage[x][y];
		loadSprites();
	}
	private BufferedImage getSprite(int x, int y){
		if(x > numSprites.x || y > numSprites.y) throw new ArrayIndexOutOfBoundsException();
		Vector2 spritePosition = new Vector2(x * spriteSize.x,y * spriteSize.y);
		BufferedImage sprite = (BufferedImage) Main.frame.createImage((int)spriteSize.x,(int)spriteSize.y);
		for(int xPos = 0;xPos < spriteSize.x;xPos++)
			for(int yPos = 0;yPos < spriteSize.y;yPos++){
				sprite.setRGB(xPos, yPos, sheet.getRGB((int)(xPos + spritePosition.x),(int)(yPos + spritePosition.y)));
			}
		return sprite;
	}
	private void loadSprites(){
		for(int x = 0;x < numSprites.x;x++)
			for(int y = 0;y < numSprites.y;y++){
				sprites[x][y] = getSprite(x,y);
			}
	}
	public Image getImage(int x,int y){return sprites[x][y];}
}
