package com.wicgames.wicLibrary;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private Vector2 spriteSize; //Size of a single sprite in sprite sheet
	private Vector2 numSprites; //Number of sprites in sprite sheet
	private BufferedImage sheet; //Sprite Sheet
	private BufferedImage[][] sprites; //Two-D array holding sprites
	private int borderSize; //Size of border around sprites in sprite sheet
	private int offset;
	private int numSpritesTotal = 0;
	/**
	 * Creates sprite sheet , splits image into sprites
	 * @param x Width of sprite
	 * @param y Height of sprite
	 * @param borderSize Size of border around sprite
	 * 
	 */
	public SpriteSheet(String path,int x,int y, int borderSize,int offset){
		spriteSize = new Vector2(x,y);
		try {
			sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.borderSize = borderSize;
		this.offset = offset;
		numSprites = new Vector2((sheet.getWidth(null) - offset) / (spriteSize.x + borderSize), (sheet.getHeight(null) - offset) / (spriteSize.y + borderSize));
		sprites = new BufferedImage[x][y];
		loadSprites();
	}
	public Vector2 getNumSprites() {
		return numSprites.copy();
	}
	
	public int getNumSpritesTotal() {
		return numSpritesTotal;
	}
	/**
	 * Gets sprite at x,y from sprite sheet
	 * @param x X Position of sprite
	 * @param y Y Position of sprite
	 * @return Sprite at x and y
	 * 
	 */
	private BufferedImage getSprite(int x, int y){
		
		if(x > numSprites.x || y > numSprites.y) throw new ArrayIndexOutOfBoundsException(); //Trying to get sprite outside of sprite sheet
		Vector2 spritePosition = new Vector2(x * spriteSize.x + offset,y * spriteSize.y + offset); //Gets position of sprite on sprite sheet
		BufferedImage sprite = new BufferedImage((int)spriteSize.x,(int)spriteSize.y,BufferedImage.TYPE_4BYTE_ABGR); //Creates new image to hold sprite
		for(int xPos = 0;xPos < spriteSize.x;xPos++) 
			for(int yPos = 0;yPos < spriteSize.y;yPos++){
				//Copy's Pixels from sprite sheet to sprite image
				int rgb = sheet.getRGB((int)(xPos + spritePosition.x + x*borderSize),(int)(yPos + spritePosition.y + y*borderSize));
				sprite.setRGB(xPos, yPos, rgb);
			}
		numSpritesTotal++;
		return sprite;
	}
	/**
	 * Loads all sprites from sprite sheet and then removes only reference to sprite sheet (Garbage Collector will remove it).
	 */
	private void loadSprites(){
		for(int x = 0;x < numSprites.x;x++)
			for(int y = 0;y < numSprites.y;y++){
				sprites[x][y] = getSprite(x,y); //Takes sprite from sprite sheet
			}
		sheet = null; //Removes reference to sprite sheet
	}
	/**
	 * Gets image from sprite array
	 */
	public Image getImage(int x,int y){return sprites[x][y];} //Gets sprite in array at x and y
	public Image getImage(int i){return sprites[(int) (i % numSprites.x)][(int) (i / numSprites.x)];} //Gets i th sprite in array
}
