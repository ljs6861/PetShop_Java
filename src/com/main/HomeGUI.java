package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dog.DogDAO;
import com.dog.DogGUI;
import com.login.LoginGUI;
import com.shop.ShopGUI;
import com.tour.GameView;

public class HomeGUI {

public HomeGUI ()  {
	
		
		JFrame frame = new JFrame("애완견 이상형 월드컵");
		frame.setLocation(350, 200);
		frame.setPreferredSize(new Dimension(1200,550));
		Container contentPane = frame.getContentPane();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		contentPane.setBackground(Color.getHSBColor(100, 116, 100));
		panel1.setBackground(Color.getHSBColor(100, 116, 100));
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		
		contentPane.add(panel2, BorderLayout.CENTER);
		ImageIcon img1 = new ImageIcon("src/data/home.png");
		
		
		JLabel ImgBox1 = new JLabel(img1);
		ImgBox1.setBounds(100,100, img1.getIconWidth(),img1.getIconHeight());
		panel2.add(ImgBox1);
		
	
		
		
		
		JButton TourButton = new JButton("애완견 월드컵");
		JButton DogButton = new JButton("애완견 리스트");
		JButton ShopButton = new JButton("애완견 구매처");
		JButton ExcelButton = new JButton("EXCEL 저장");
		JButton LogoutButton = new JButton("로그아웃");
		

		
		
		//--------------------------------------------------------------
		panel1.add(TourButton);
		panel1.add(DogButton);
		panel1.add(ShopButton);
		panel1.add(ExcelButton);
		panel1.add(LogoutButton);
	
		contentPane.add(panel1, BorderLayout.WEST);
	
		
		
		/*
		 * 
		 */
		
		//--------------------------------------------------------------
		// 토너먼트(월드컵) 버튼 클릭시
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				GameView game = new GameView();
				frame.dispose();
			}
		};
		TourButton.addActionListener(obj1);
		
		//--------------------------------------------------------------
		//애완견 리스트 클릭시
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				
				DogGUI dog = new DogGUI();
				DogDAO.allRemoveRowData(dog.model());  //애완견GUI 클릭시 list에 winner(우승견) 
													   //남아있는 현상 방지하기 위해  테이블 초기화
				
			
				frame.dispose();
			}
		};
		DogButton.addActionListener(obj2);
		
		//--------------------------------------------------------------
		//애견샵 구매처 클릭시
		ActionListener obj3 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				
				ShopGUI shop = new ShopGUI();
				frame.dispose();
			}
		};
		ShopButton.addActionListener(obj3);
		
		//--------------------------------------------------------------
		//엑셀 저장 클릭시
		ActionListener obj4 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {

				
			ExcelGUI excelgui = new ExcelGUI();
					
			}
		};
		ExcelButton.addActionListener(obj4);
		
		//--------------------------------------------------------------
		//로그아웃 클릭시
		ActionListener obj5 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {

				LoginGUI loginGui = new LoginGUI();
				frame.dispose();
			
			}
		};
		LogoutButton.addActionListener(obj5);
		
		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}

