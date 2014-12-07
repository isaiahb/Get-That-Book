package com.wicgames.wicLibrary;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageMethods {
	public static void flipImage(BufferedImage bi){
		BufferedImage tmp = copyImage(bi);
		for(int x = 0;x < bi.getWidth();x++)
			for(int y = 0;y < bi.getHeight();y++)
				bi.setRGB(x, y, tmp.getRGB(bi.getWidth() - x - 1,y));
	}
	public static BufferedImage copyImage(BufferedImage source){
	    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
}
