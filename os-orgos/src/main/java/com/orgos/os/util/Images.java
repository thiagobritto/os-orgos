package com.orgos.os.util;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Images {

	private static final String resource = Images.class.getResource("/").getPath();
	
	public static Image getImage(String filename) {
		return Toolkit.getDefaultToolkit().getImage(resource + filename);
	}
	
	public static ImageIcon getImageIcon(String filename) {
		Image image = getImage(filename);
		return new ImageIcon(image);
	}
	
	public static ImageIcon getImageIcon(String filename, int width, int height) {
		Image image = getImage(filename);
		return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
	public static Image getImageLogo() {
		return getImage("images/logo_icon_48x48.png");
	}
	
	public static ImageIcon getImageIconBtnNew() {
		return getImageIcon("images/new_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnEdit() {
		return getImageIcon("images/edit_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnRemove() {
		return getImageIcon("images/remove_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnSave() {
		return getImageIcon("images/check_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnCancel() {
		return getImageIcon("images/cancel_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnPrint() {
		return getImageIcon("images/print_icon_48x48.png", 16, 16);
	}
	
	public static ImageIcon getImageIconBtnSearch() {
		return getImageIcon("images/search_icon_48x48.png", 16, 16);
	}
}
