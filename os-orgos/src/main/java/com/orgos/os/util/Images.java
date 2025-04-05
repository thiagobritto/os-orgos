package com.orgos.os.util;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Images {

	private static String resource = Images.class.getResource("/images/").getPath();
	
	public static Image getImage(String source) {
		return Toolkit.getDefaultToolkit().getImage(resource + source);
	}
	
	public static ImageIcon getImageIcon(String source) {
		Image image = getImage(source);
		return new ImageIcon(image);
	}
	
	public static ImageIcon getImageIcon(String source, int width, int height) {
		Image image = getImage(source);
		return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
}
