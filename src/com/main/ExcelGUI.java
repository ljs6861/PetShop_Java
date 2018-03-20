package com.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dog.DogEXCEL;
import com.shop.ShopEXCEL;

public class ExcelGUI {

public ExcelGUI ()  {
	
	
		JFrame frame = new JFrame("EXCEL 저장");
		frame.setLocation(780,350);
		frame.setPreferredSize(new Dimension(400,150));
		Container contentPane = frame.getContentPane();
		
	
	
	
	JButton DogExcelButton = new JButton("애완견 EXCEL 저장");
	JButton ShopExcelButton = new JButton("애견샵 EXCEL 저장");
	
	DogExcelButton.setBounds(30,20,150,50);
	ShopExcelButton.setBounds(200,20,150,50);
	
	
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(null);
		panel1.add(DogExcelButton);
		panel1.add(ShopExcelButton);
		
		contentPane.add(panel1);
		
		
		//-------------------------------------------------------------
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {

				try {
					DogEXCEL dogexcel = new DogEXCEL();
					JOptionPane.showMessageDialog(null, "엑셀 저장완료 (애완견)");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		};
		DogExcelButton.addActionListener(obj1);
		
		//------------------------------------------------------------
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {

				try {
					ShopEXCEL shopexcel = new ShopEXCEL();
					JOptionPane.showMessageDialog(null, "엑셀 저장완료 (애견샵)");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		};
		ShopExcelButton.addActionListener(obj2);
		
		
		
		frame.pack();
		frame.setVisible(true);
	}

	

}
