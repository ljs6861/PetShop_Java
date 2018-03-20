package com.tour;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dog.DogGUI;


public class WinnerView extends JFrame{
	
	
						private static final LayoutManager FlowLayout = null;
	static String winDog;
	Data data = new Data();
	public WinnerView(){
		
	}
	
						//우승개 이미지이름
	public WinnerView(String winner){
//		winDog = winner;
		winDog = data.printName(winner);
		
		JFrame frame = new JFrame("애완견 이상형 월드컵");
		frame.setLocation(320, 200);

		Container contentPane = frame.getContentPane();
		
		JPanel panel = new JPanel();
		
		Img imgWinner = new Img(winner, "", 615, 90, 400, 400);
		imgWinner.draw(panel);
							//우승패널 배경이미지
		Img img = new Img("Winner.png", "", 0, 0, 1100, 600);
		img.draw(panel);

		panel.setLocation(250, 200);
		panel.setBounds(0, 0, 1400, 750);
		panel.setLayout(FlowLayout);
	
//		JButton button1 = new JButton("우승견 정보");
//		button1.setBounds(900, 10, 130, 40);
		JButton button2 = new JButton("토너먼트 랭킹");
		button2.setBounds(900, 10, 130, 40);
		
//		panel.add(button1);
		panel.add(button2);
		
		contentPane.add(panel);
		
		//우승자 화면에서 애완견 리스트로
//		ActionListener obj2 = new ActionListener()  {
//			public void actionPerformed (ActionEvent e)  {
//				
//				DogGUI dog = new DogGUI();
//				
//				frame.dispose();
//			}
//		};
//		button1.addActionListener(obj2);
		
		//우승자 화면에서 랭킹 화면으로
		ActionListener obj3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankView rank = new RankView();
				
				frame.dispose();
			}
		};
		button2.addActionListener(obj3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(1150,650);
		frame.setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - 1120) / 2), (int) ((Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 650) / 2), 1120, 650);
		frame.setVisible(true);
		
		JOptionPane.showMessageDialog(null, winDog + "이(가) 우승하였습니다!!");
		
	}
	

	
	public String getWinDog() {
		return winDog;
	}
	
	public void setWinDog(String winDog){
		this.winDog = winDog;
	}
	
}
