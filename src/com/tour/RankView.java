package com.tour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dog.DogGUI;

public class RankView extends JFrame {

	JFrame jFrame;
	public RankView(){
	jFrame = new JFrame();
	jFrame.setLocation(320,200);
	jFrame.setTitle("토너먼트 랭킹");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawPanel drawpanel = new DrawPanel();
		
		JButton back = new JButton("우승자 정보");
		back.addActionListener(new BackMenu());
		back.setBounds(1000, 20, 130, 40);
		drawpanel.add(back);
		
		//8강
		int x = 0;
		for(int i = 0; i < 8; i++){
			Img rankImg = new Img(GameView.entry8[i],"", x, 480, 120, 120);
			rankImg.draw(drawpanel);
			x += 153;
		}
		//4강
		int x2 = 78;
		for(int i = 0; i < 4; i++){
			Img rankImg = new Img(GameView.entry4[i],"", x2, 320, 120, 120 );
			rankImg.draw(drawpanel);
			x2 += 306;
		}
		
		//결승
		Img rankImg = new Img(GameView.entry2[0],"", 231, 160, 120, 120);
		rankImg.draw(drawpanel);
		Img rankImg2 = new Img(GameView.entry2[1],"", 843, 160, 120, 120);
		rankImg2.draw(drawpanel);
		
		
	
		
		//우승자
		Img rankImg3 = new Img(GameView.winner, "", 537, 0,	120, 120);
		rankImg3.draw(drawpanel);
		
		Img backGround = new Img("League.jpg", "", 447, 140, 300, 300);
		backGround.draw(drawpanel);
		
		
		
		
		ImageIcon img1 = new ImageIcon("src/data/Win.png");
		JLabel ImgBox1 = new JLabel(img1);
		ImgBox1.setBounds(665, 0, 200, 130);
		ImgBox1.isBackgroundSet();
	
		drawpanel.add(ImgBox1);
		
		
		ImageIcon img2 = new ImageIcon("src/data/top.jpg");
		JLabel ImgBox2 = new JLabel(img2);
		ImgBox2.setBounds(30, 10, 225, 170);
		ImgBox2.isBackgroundSet();
	
		drawpanel.add(ImgBox2);
		
		ImageIcon img3 = new ImageIcon("src/data/cup.jpg");
		JLabel ImgBox3 = new JLabel(img3);
		ImgBox3.setBounds(530, 150, 130, 200);
		ImgBox3.isBackgroundSet();
	
		drawpanel.add(ImgBox3);
		
		
		
				
		drawpanel.setLayout(null);
		drawpanel.setLocation(320, 200);
		drawpanel.setBounds(30, 0, 1210, 900);
		
		drawpanel.setBackground(Color.WHITE);
		
		jFrame.add(drawpanel);
		jFrame.setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - 1215) / 2), (int) ((Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 670) / 2), 1215, 670);
		jFrame.setVisible(true);
		
	}
	class DrawPanel extends JPanel{
		
		@Override
		public void paint(Graphics g){
			super.paint(g);
			//8강대진
			int x = 60;
			for(int i = 0; i < 4; i++){
				g.drawLine(x , 450, 153 + x, 450);
				g.drawLine(x, 450, x, 470);
				g.drawLine(153 + x, 450, 153 + x, 470);
				x += 306;
			}
			
			//4강대진
			g.drawLine(138, 290, 444, 290);
			g.drawLine(138, 290, 138, 310);
			g.drawLine(444, 290, 444, 310);
			
			g.drawLine(750, 290, 1056, 290);
			g.drawLine(750, 290, 750, 310);
			g.drawLine(1056, 290, 1056, 310);
			
			//결승
			g.drawLine(291, 130, 903, 130);
			g.drawLine(291, 130, 291, 150);
			g.drawLine(903, 130, 903, 150);
		}
	}
	class BackMenu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DogGUI dog = new DogGUI();
			jFrame.dispose();
			
		}
		
	}
}