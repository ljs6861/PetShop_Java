package com.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.main.HomeGUI;

public class LoginGUI {

	static DefaultTableModel model;

	public LoginGUI() {
		
		

		JFrame frame = new JFrame("펫월드");

		Container contentPane = frame.getContentPane();
		JPanel panel1 = new JPanel();

		contentPane.setBackground(Color.getHSBColor(100, 116, 100));
		panel1.setBackground(Color.getHSBColor(100, 116, 100));

		panel1.setLayout(null);

		ImageIcon img1 = new ImageIcon("src/data/login.jpg");
		JLabel ImgBox1 = new JLabel(img1);
		ImgBox1.setBounds(4, 10, 320, 330);
		ImgBox1.isBackgroundSet();

		JLabel id = new JLabel("ID");
		id.setFont(new Font("Gulim", Font.BOLD, 20));
		id.setBounds(30, 350, 60, 50);

		JTextField txtid = new JTextField(10);
		txtid.setHorizontalAlignment(JTextField.CENTER);
		txtid.setBounds(100, 350, 205, 40);

		JLabel passwd = new JLabel("비밀번호");
		passwd.setFont(new Font("Gulim", Font.BOLD, 15));
		passwd.setBounds(30, 400, 70, 50);

		JTextField txtpasswd = new JTextField(10);
		txtpasswd.setHorizontalAlignment(JTextField.CENTER);
		txtpasswd.setBounds(100, 400, 205, 40);

		JButton LoginButton = new JButton("로그인");
		JButton ResistButton = new JButton("회원가입");

		LoginButton.setBounds(30, 450, 127, 50);
		ResistButton.setBounds(170, 450, 127, 50);

		String columNames[] = { "ID", "비밀번호", "이름", "전화번호" };
		DefaultTableModel model = new DefaultTableModel(columNames, 0);
		JTable table = new JTable(model);
		this.model = model;
		LoginDAO.printTable(table); // 프로그램 실행 시 테이블 화면 초기화

		contentPane.add(new JScrollPane(table));

		// --------------------------------------------------------------
		panel1.add(ImgBox1);
		panel1.add(id);
		panel1.add(txtid);
		panel1.add(passwd);
		panel1.add(txtpasswd);

		panel1.add(LoginButton);
		panel1.add(ResistButton);

		contentPane.add(panel1);
	
		/*
		 * 
		 */

		// --------------------------------------------------------------

		// ID,비밀번호 입력후 로그인 클릭시
		ActionListener obj1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ID = txtid.getText();
				String 비밀번호 = txtpasswd.getText();

				
				if (LoginDAO.LoginSearch(ID, 비밀번호) == 1) {		// 인증성공

					HomeGUI home = new HomeGUI();
					JOptionPane.showMessageDialog(null, "펫월드에 오신걸 환영합니다!");
					frame.dispose();
				}
				
				
				if (LoginDAO.LoginSearch(ID, 비밀번호) == 0) {		//

					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");

				}


				if (LoginDAO.LoginSearch(ID, 비밀번호) == -1) {

					JOptionPane.showMessageDialog(null, "해당 아이디가 없습니다.");

				}

			

			
			}
		};
		LoginButton.addActionListener(obj1);

		// --------------------------------------------------------------
		//회원가입 버튼 클릭시
		ActionListener obj2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addResistButtonGui();
				frame.dispose();
			}
		};
		ResistButton.addActionListener(obj2);

		// --------------------------------------------------------------

		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocation(600, 200);
		frame.setSize(350, 580);

		frame.setVisible(true);
		

	}

	// 회원가입 버튼 누르면 나오는 GUI
	public void addResistButtonGui() { 
		JFrame frame = new JFrame("펫월드");
		frame.setLocation(270, 220);
		frame.setPreferredSize(new Dimension(310, 300));
		Container contentPane = frame.getContentPane();

		JTextField ID = new JTextField();
		JTextField 비밀번호 = new JTextField();
		JTextField 이름 = new JTextField();
		JTextField 전화번호 = new JTextField();

		JButton button = new JButton("확인");
		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridLayout(7, 2));

		JLabel label = new JLabel(" 회원정보");
		label.setFont(new Font("Gulim", Font.BOLD, 24));
		label.setForeground(Color.getHSBColor(150, 150, 150));
		panel1.add(label);

		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel());
		panel1.add(new JLabel("  ID"));
		panel1.add(ID);
		panel1.add(new JLabel("  비밀번호"));
		panel1.add(비밀번호);
		panel1.add(new JLabel("  이름"));
		panel1.add(이름);
		panel1.add(new JLabel("  전화번호"));
		panel1.add(전화번호);

		contentPane.setBackground(Color.getHSBColor(100, 116, 100));
		panel1.setBackground(Color.getHSBColor(100, 116, 100));

		contentPane.add(panel1);
		contentPane.add(button, BorderLayout.SOUTH);

		ActionListener obj1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1 = ID.getText();
				String str2 = 비밀번호.getText();
				String str3 = 이름.getText();
				String str4 = 전화번호.getText();

				LoginDAO.LoginInsert(str1, str2, str3, str4);
				JOptionPane.showMessageDialog(null, "회원가입이 되셨습니다.");
				LoginGUI LoginGui = new LoginGUI();
				frame.dispose();
			}
		};
		button.addActionListener(obj1);

		frame.setLocation(600, 300);
		frame.pack();
		frame.setVisible(true);
	}

}
