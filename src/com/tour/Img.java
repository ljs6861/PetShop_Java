package com.tour;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Img extends JLabel{

	ImageIcon imgIcon;
	private String urlhead = "src/data/";
	
	private String imgName;
	private String dogName;

	public Img(){
		
	}
	
	public Img(String _imgName, String _dogName, int x, int y, int width, int height){
		imgName = _imgName;
		dogName = _dogName;
		
		if(_imgName == null){
			imgIcon = new ImageIcon(urlhead + "img_null.png");
		}else{
			imgIcon = new ImageIcon(urlhead + _imgName);
		}
		
		setIcon(imgIcon);
		setBounds(x, y, width, height);
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgIcon.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), 0, 0, imgIcon.getIconWidth(),
				imgIcon.getIconHeight(), this);
	}

	//이미지 출력
	public void setImgName(String name) {
		imgIcon = new ImageIcon(urlhead + name);
		this.setIcon(imgIcon);
	}
	
	public String getImgName() {
		return imgName;
	}
	
	public String getDogName(){
		return dogName;
	}

	public void draw(JPanel view){
		view.add(this);
	}
}
