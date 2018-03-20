package com.dog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.main.HomeGUI;
import com.tour.WinnerView;

public class DogGUI {
	static DefaultTableModel model;
	
	WinnerView view = new WinnerView();	
	String win=view.getWinDog();
	
	public DogGUI ()  {
		JFrame frame = new JFrame("애완견 리스트");
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
		
		
		
		

		//--------------------------------------------------------------
		JButton addDogButton = new JButton("애완견 추가");
		JButton deleteDogButton = new JButton("애완견 삭제");
		
		//--------------------------------------------------------------
		JButton allListButton = new JButton("애완견 리스트");
		
		//--------------------------------------------------------------
		JButton backButton = new JButton("뒤로 가기");
		
		

		JTextField 품종 = new JTextField(10);

		
		JTextField 국적 = new JTextField(10);
		JTextField 크기 = new JTextField(10);
		JTextField 털빠짐 = new JTextField(10);
		JTextField 성격 = new JTextField(10);
		JTextField 분양가 = new JTextField(10);
		JButton indexButton = new JButton("검색");
		


		
		
		/*
		 * 
		 */
		
		
		
	
		//--------------------------------------------------------------
		panel1.add(addDogButton);
		panel1.add(deleteDogButton);
		panel1.add(backButton);
		contentPane.add(panel1, BorderLayout.EAST);
		
		//--------------------------------------------------------------
		panel2.add(allListButton);
		contentPane.add(panel2, BorderLayout.NORTH);
		
		//--------------------------------------------------------------
		panel3.add(new JLabel("품종"));
		panel3.add(품종);
		panel3.add(new JLabel("크기"));
		panel3.add(크기);
		panel3.add(new JLabel("털빠짐"));
		panel3.add(털빠짐);
		panel3.add(indexButton);
		contentPane.add(panel3, BorderLayout.SOUTH);
		
		//--------------------------------------------------------------
		
		
		
		String columNames[] = {"품종", "국적", "크기", "털빠짐", "성격", "분양가"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);
		JTable table = new JTable(model);
		
		TableColumn col성격 = table.getColumn("성격");
		col성격.setPreferredWidth(120);
		
		this.model = model;
		DogDAO.printTable(table); // 프로그램 실행 시 테이블 화면 초기화 
	
		contentPane.add(new JScrollPane(table));
 
		
	
		
	
		
		DogDAO.DogSelect2(model, win);
			
		
	
		
		
		
		/*
		 * 
		 * 
		 */
		
		
		
		
		
		//--------------------------------------------------------------
		//애완견 추가 클릭시
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				
				addDogButtonGui();    // 밑에 있음 DogInsert 
			}
		};
		addDogButton.addActionListener(obj1);
	
	
	
	
		// 애완견 삭제
		ActionListener obj2 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				deleteDogButtonGui();   // 밑에 있음 DogDelete
			}
		};
		deleteDogButton.addActionListener(obj2);
		
		
		//--------------------------------------------------------------
		//애완견 리스트 클릭시
		ActionListener obj3 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				
			
				DogDAO.DogSelect(model);
				
			}
		};
		allListButton.addActionListener(obj3);
		
		
		//--------------------------------------------------------------
		//애완견 검색
		ActionListener obj4 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				
				
				String 품종Str = 품종.getText();
				
				
				String 크기Str = 크기.getText();
				String 털빠짐Str = 털빠짐.getText();
				if (품종Str.equals("") && 크기Str.equals("") &&털빠짐Str.equals(""))  {
					if (!털빠짐Str.equals(""))  {
						DogDAO.allRemoveRowData(model);
						
						return;
					}	
					else  {
						System.out.println("검색조건을 한개 이상 입력해주세요");
						return;
					}
				}
				DogDAO.allRemoveRowData(model);
				DogDAO.DogSearch(품종Str, 크기Str, 털빠짐Str, table);
			}
		};
		indexButton.addActionListener(obj4);
		
		//--------------------------------------------------------------
		//뒤로가기
		ActionListener obj5 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
		
				
				HomeGUI homeGui = new HomeGUI();
				
				frame.dispose();
			}
		};
		backButton.addActionListener(obj5);
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public DefaultTableModel model(){
		return model;
	}
	
	/*
	 * 
	 * 
	 */
	
	
	// 애완견추가 버튼 누르면 나오는 GUI
	public void addDogButtonGui()  { 
		JFrame frame = new JFrame("추가할 애완견 정보 입력");
		frame.setLocation(500,400);
		frame.setPreferredSize(new Dimension(400,200));
		Container contentPane = frame.getContentPane();
		
		JTextField 품종 = new JTextField();
		JTextField 국적 = new JTextField();
		JTextField 크기 = new JTextField();
		JTextField 털빠짐 = new JTextField();
		JTextField 성격 = new JTextField();
		JTextField 분양가 = new JTextField();
		
		JButton button = new JButton("확인");
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(6, 2));
		panel1.add(new JLabel("품종"));
		panel1.add(품종);
		panel1.add(new JLabel("국적"));
		panel1.add(국적);
		panel1.add(new JLabel("크기"));
		panel1.add(크기);
		panel1.add(new JLabel("털빠짐"));
		panel1.add(털빠짐);
		panel1.add(new JLabel("성격"));
		panel1.add(성격);
		panel1.add(new JLabel("분양가"));
		panel1.add(분양가);
		
		contentPane.add(panel1);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String str1 = 품종.getText();
				String str2 = 국적.getText();
				String str3 = 크기.getText();
				String str4 = 털빠짐.getText();
				String str5 = 성격.getText();
				String str6 = 분양가.getText();
				
				DogDAO.DogInsert(str1, str2, str3, str4, str5, str6);
		
				frame.dispose();
			}
		};
		button.addActionListener(obj1);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//--------------------------------------------------------------
	// 애완견 삭제 버튼 누르면 나오는 GUI
	public void deleteDogButtonGui()  { 
		JFrame frame = new JFrame("삭제할 애완견 정보 입력");
		frame.setLocation(500,400);
		frame.setPreferredSize(new Dimension(270,100));
		Container contentPane = frame.getContentPane();
		JTextField code = new JTextField();
		JButton button = new JButton("확인");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new JLabel("품종"));
		panel.add(code);
		contentPane.add(panel);
		contentPane.add(button, BorderLayout.SOUTH);
		
		ActionListener obj1 = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {
				String codeStr = code.getText();
		
				DogDAO.DogDelete(codeStr, win);
				frame.dispose();
			}
		};
		button.addActionListener(obj1);
		
	
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	
	
}
