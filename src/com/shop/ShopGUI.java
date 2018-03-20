package com.shop;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import com.main.HomeGUI;

public class ShopGUI {
	static DefaultTableModel model;
	
	public ShopGUI ()  {
		JFrame frame = new JFrame("애완견 구매처");
		frame.setLocation(350, 200);
		frame.setPreferredSize(new Dimension(1200,600));
		Container contentPane = frame.getContentPane();
		
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		
		contentPane.setBackground(Color.getHSBColor(100, 116, 100));
		panel1.setBackground(Color.getHSBColor(100, 116, 100));
		panel2.setBackground(Color.getHSBColor(100, 116, 100));
		panel3.setBackground(Color.getHSBColor(100, 116, 100));
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		
		JButton backButton = new JButton("뒤로 가기");
		//--------------------------------------------------------------
		JButton addShopButton = new JButton("애견샵 추가");
		JButton deleteShopButton = new JButton("애견샵 삭제");
		
		//--------------------------------------------------------------
		JButton allListButton = new JButton("애견샵 리스트");
	
		//--------------------------------------------------------------
		JTextField 애견샵 = new JTextField(10);
		JTextField 지역 = new JTextField(10);
		JTextField 주소 = new JTextField(10);
		JTextField 연락처 = new JTextField(10);
		JTextField 사이트 = new JTextField(10);

		JButton indexButton = new JButton("검색");
		
		/*
		 * 
		 */
		
		//--------------------------------------------------------------
		panel1.add(addShopButton);
		panel1.add(deleteShopButton);
		panel1.add(backButton);
		contentPane.add(panel1, BorderLayout.EAST);
		
		//--------------------------------------------------------------
		panel2.add(allListButton);
		contentPane.add(panel2, BorderLayout.NORTH);
		
		//--------------------------------------------------------------
		panel3.add(new JLabel("애견샵"));
		panel3.add(애견샵);
		panel3.add(new JLabel("주소"));
		panel3.add(주소);
		panel3.add(new JLabel("연락처"));
		panel3.add(연락처);
		panel3.add(indexButton);
		contentPane.add(panel3, BorderLayout.SOUTH);
		
		
		//--------------------------------------------------------------
		String columNames[] = {"애견샵", "지역", "주소", "연락처", "사이트"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);
		JTable table = new JTable(model);
		
		
		TableColumn col주소 = table.getColumn("주소");
		col주소.setPreferredWidth(200);
		TableColumn col사이트 = table.getColumn("사이트");
		col사이트.setPreferredWidth(170);
		
		
		this.model = model;
		ShopDAO.printTable(table); // 프로그램 실행 시 테이블 화면 초기화 
		contentPane.add(new JScrollPane(table));
	
		/*
		 * 
		 */
		
		//--------------------------------------------------------------
		//애견샵 추가 
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				addShopButtonGui();
			}
		};
		addShopButton.addActionListener(obj1);
		
		//--------------------------------------------------------------
		// 애견샵 삭제
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				deleteShopButtonGui();
			}
		};
		deleteShopButton.addActionListener(obj2);
		
		//--------------------------------------------------------------
		//애견샵 리스트
		ActionListener obj3 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				ShopDAO.ShopSelect(model);
			}
		};
		allListButton.addActionListener(obj3);
		
	
		//--------------------------------------------------------------
		//애견샵 검색
		ActionListener obj4 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String 애견샵Str = 애견샵.getText();
				String 주소Str = 주소.getText();
				String 연락처Str = 연락처.getText();
				if (애견샵Str.equals("") && 주소Str.equals("") &&연락처Str.equals(""))  {
					if (!연락처Str.equals(""))  {
						ShopDAO.allRemoveRowData(model);
						
						return;
					}	
					else  {
						System.out.println("검색조건을 한개 이상 입력해주세요");
						return;
					}
				}
				ShopDAO.allRemoveRowData(model);
				ShopDAO.ShopSearch(애견샵Str, 주소Str, 연락처Str, table);
			}
		};
		indexButton.addActionListener(obj4);
		
		//뒤로가기
		ActionListener obj5 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
		
				HomeGUI homeGui = new HomeGUI();
				frame.dispose();
			}
		};
		backButton.addActionListener(obj5);
		
		
		
		//--------------------------------------------------------------
	
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/*
	 * 
	 * 
	 */
	
	//--------------------------------------------------------------
	// 애견샵 추가 버튼 누르면 나오는 GUI
	public void addShopButtonGui()  { 
		JFrame frame = new JFrame("추가할 애완견 정보 입력");
		frame.setLocation(500,400);
		frame.setPreferredSize(new Dimension(400,200));
		Container contentPane = frame.getContentPane();
		
		JTextField 애견샵 = new JTextField();
		JTextField 지역 = new JTextField();
		JTextField 주소 = new JTextField();
		JTextField 연락처 = new JTextField();
		JTextField 사이트 = new JTextField();
	
		
	
		JButton button = new JButton("확인");
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 2));
		panel1.add(new JLabel("애견샵"));
		panel1.add(애견샵);
		panel1.add(new JLabel("지역"));
		panel1.add(지역);
		panel1.add(new JLabel("주소"));
		panel1.add(주소);
		panel1.add(new JLabel("연락처"));
		panel1.add(연락처);
		panel1.add(new JLabel("사이트"));
		panel1.add(사이트);
		
		
		contentPane.add(panel1);
		contentPane.add(button, BorderLayout.SOUTH);
		
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String str1 = 애견샵.getText();
				String str2 = 지역.getText();
				String str3 = 주소.getText();
				String str4 = 연락처.getText();
				String str5 = 사이트.getText();
				
				
				
				ShopDAO.ShopInsert(str1, str2, str3, str4, str5);
				
		
				frame.dispose();
				
			

			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//--------------------------------------------------------------
	// 애견샵 삭제 버튼 누르면 나오는 GUI
	public void deleteShopButtonGui()  { 
		JFrame frame = new JFrame("삭제할 애견샵 정보 입력");
		frame.setLocation(500,400);
		frame.setPreferredSize(new Dimension(270,100));
		Container contentPane = frame.getContentPane();
		JTextField code = new JTextField();
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new JLabel("애견샵"));
		panel.add(code);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String codeStr = code.getText();
				ShopDAO.ShopDelete(codeStr);
				frame.dispose();
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	
	
}
