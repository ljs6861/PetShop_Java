package com.tour;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.main.HomeGUI;



public class GameView extends JFrame {
	private static final LayoutManager FlowLayout = null;
	Img imgLeft;
	Img imgRight;
	Img[] img = new Img[32];
	
	JButton btn;
	JButton btnStart;
	JLabel versus;
	
	int roundCount = 1;
	int clickCount = 0;
	boolean roundSwitch = false;
	
	int ROUND = 32;
	JPanel panel;
	JFrame frame;

	GameView gameView;
	Data data = new Data();
	static String winner;
	
	LineBorder border = new LineBorder(Color.BLUE, 4, true);
	
	
	public GameView(){
		
		JFrame frame = new JFrame("애완견 이상형 월드컵");
		frame.setLocation(320, 200);
		
		frame.setPreferredSize(new Dimension(1220,645));
		frame.setBounds((int) ((Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - 1220) / 2), (int) ((Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() - 645) / 2), 1220, 645);
		Container contentPane = frame.getContentPane();
		
		panel = new JPanel();
		
		panel.setLocation(250, 200);
		panel.setBounds(0, 0, 1220, 645);
		
		data.loadData();
		
		imgLeft = new Img("","", 50, 20, 500, 500);
		imgLeft.draw(panel);
      imgLeft.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
				imgLeft.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(clickCount > 0){
				imgLeft.setBorder(border);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(roundSwitch){
				Left();
				}
				
			}
		});
		
		
		
		
		imgRight = new Img("", "", 650, 20, 500, 500);
		imgRight.draw(panel);
imgRight.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				imgRight.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(clickCount > 0 ){
					imgRight.setBorder(border);
				}
				
			
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(roundSwitch){
					Right();
				}
				
			}
		});
		
		
		
		
		
		panel.setLayout(FlowLayout);
		contentPane.add(panel);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		panel.setLayout(null);
		
		btn = new JButton("START");
		btn.setBounds(515, 220, 180, 70);
		btn.setBackground(Color.getHSBColor(150,231, 150));
		btn.setForeground(Color.RED);
		btn.setFont(new Font("Impact", Font.BOLD, 40));
		
		JButton backbutton = new JButton("뒤로 가기");
		backbutton.setBounds(1050, 535, 120, 40);
		
		
//		btn.setOpaque(false);
		
		panel.add(btn);
		panel.add(backbutton);
		
		//화면 아래에 강아지 32마리 이미지 보이기
		int num = 0;
		int y = 0;
		for(int i = 0; i < 4;i++){
			int x = 0;
			for(int j = 0; j < 8; j++){
				img[num] = new Img(data.getDogs().get(num).getImgName(),
						data.getDogs().get(j).getDogName(), x, y, 150, 136);
				img[num].draw(panel);
				num ++;
				x += 150;
			}
			
			y += 136;
		}
		
		
		ActionListener back = new ActionListener()  {
			public void actionPerformed (ActionEvent e)  {

				HomeGUI home = new HomeGUI();
				frame.dispose();
			
			}
		};
		backbutton.addActionListener(back);
		
		
		
		
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn.setVisible(false);
				
				if(!btn.getText().equals("WINNER")){
					if(roundCount == 1){
						
						JOptionPane.showMessageDialog(null, "32강이 시작됩니다!");
						//32강 첫경기.
						//하단 32마리강아지 비활성
						for(int i = 0; i < 32; i++){
							img[i].setVisible(false);
						}
						
						versus.setVisible(true); // VS
						imgLeft.setImgName(data.getDogs().get(0).getImgName());
						imgRight.setImgName(data.getDogs().get(1).getImgName()); 
						roundSwitch = true; // 좌 우 이미지 마우스클릭 활성화
					} else if(roundCount == 2){
						
						JOptionPane.showMessageDialog(null, "16강이 시작됩니다!");
						//16강 첫경기.
						for(int i = 0; i < 32; i++){
							img[i].setVisible(false);
						}
						
						versus.setVisible(true);
						imgLeft.setImgName(entry16[0]);
						imgRight.setImgName(entry16[1]);
						roundSwitch = true;
					} else if(roundCount == 3){
						
						JOptionPane.showMessageDialog(null, "8강이 시작됩니다!");
						//8
						for(int i = 0; i < 32; i++){
							img[i].setVisible(false);
						}
						
						versus.setVisible(true);
						imgLeft.setImgName(entry8[0]);
						imgRight.setImgName(entry8[1]);
						roundSwitch = true;
					} else if(roundCount == 4){
						
						JOptionPane.showMessageDialog(null, "4강이 시작됩니다!");
						//4
						for(int i = 0; i < 32; i++){
							img[i].setVisible(false);
						}
						versus.setVisible(true);
						imgLeft.setImgName(entry4[0]);
						imgRight.setImgName(entry4[1]);
						roundSwitch = true;
					} else if(roundCount == 5){
						
						JOptionPane.showMessageDialog(null, "결승전입니다!");
						//결승
						versus.setVisible(true);
						imgLeft.setImgName(entry2[0]);
						imgRight.setImgName(entry2[1]);
						roundSwitch = true;
					}
				
				clickCount++;
				}
				//우승자출력
				else {
					WinnerView winView = new WinnerView(winner);
					
					frame.dispose();
					
				}
			}
		});
		
		
		versus = new JLabel("VS");
		versus.setFont(new Font("Impact", Font.BOLD, 65));
		versus.setForeground(Color.getHSBColor(150,150, 150));
		versus.setBounds(564, 160, 200, 200);
		panel.add(versus);
	
		versus.setVisible(false);
		
	}
	
	
	


	
	String[] entry16 = new String[16];
	static String[] entry8 = new String[8];
	static String[] entry4 = new String[4];
	static String[] entry2 = new String[2];
	

	public LineBorder border(){
		return border;
	}
	
	
	public JPanel getPanel(){
		return panel;
	}
	//마지막경기종료시
	public void option(){
		//좌 우 이미지 비활성
		imgLeft.setImgName("");
		imgRight.setImgName("");
		
		//배경 강아지들 활성
		if(roundCount < 4){
			for(int k = 0; k < 32; k++){
				img[k].setVisible(true);
			}
		}
		
		roundSwitch = false; //이미지 클릭 비활성
		roundCount++; // 라운드 증가
		clickCount = -1; //클릭 초기화
		ROUND /= 2;
		btn.setVisible(true); // 라운드시작버튼
		versus.setVisible(false); // VS
		imgLeft.setBorder(null); //좌 우 이미지 테두리 잔상 제거.
		imgRight.setBorder(null);
		j = 0;
	}
	
	int j = 0;
	public void Left(){
		//32강
		if(roundCount == 1){
			
			for(int i = 0; i < 16; i++){ // 16 경기
				//클릭한 수 보다 크면 break;
				if(clickCount <= i){
					break;
				}
				if(clickCount == i+1){
					//왼쪽이미지를 16강대진표에 저장
					entry16[i] = img[j].getImgName();
					img[j+1].setImgName("");
					
					j += 2;
					if(j > 30){ // 32강 마지막경기 이후 ( img[30] vs img[31] 이후 )
						option();
						break;
					} 
					// img[0] vs img[1] / img[2] vs imt[3] . . . . img[30] vs img[31]
					imgLeft.setImgName(img[j].getImgName());
					imgRight.setImgName(img[j+1].getImgName());
				}//end if
			}//end for
		}// end 32강
		
		//16강
		else if (roundCount == 2){
			for(int i = 0; i < 8; i++){ // 8경기
				if(clickCount == i+1){
					if(clickCount < i) break;
					
					entry8[i] = entry16[j];
					//배경 탈락개 이미지 제거
					for(int q = 0; q < 32; q++){
						if(entry16[j+1] == img[q].getImgName()){
							img[q].setImgName("");
							break;
						}
					}
					
					j += 2;
					
					if(j > 14){ // 16강 마지막경기 이후 
						option();
						break;
					}
					
					imgLeft.setImgName(entry16[j]);
					imgRight.setImgName(entry16[j+1]);
				}
			}//end for
		}// end 16
		
		// 8강
		else if(roundCount == 3){
			for(int i = 0; i < 4; i++){
				if(clickCount == i+1){
					if(clickCount < i) break;
					
					entry4[i] = entry8[j];
					for(int q = 0; q < 32; q++){
						if(entry8[j+1] == img[q].getImgName()){
							img[q].setImgName("");
							break;
						}
					}
					
					
					j += 2;
					if(j > 6){
						option();
						break;
					}
					
					imgLeft.setImgName(entry8[j]);
					imgRight.setImgName(entry8[j+1]);
				}
			}
		} // end 8
		
		// 4강
		else if(roundCount == 4 && clickCount == 1){
			entry2[0] = entry4[0];
			imgLeft.setImgName(entry4[2]);
			imgRight.setImgName(entry4[3]);
		} else if(roundCount == 4 && clickCount == 2){
			entry2[1] = entry4[2];
			option();
		}
		
		else if(roundCount == 5 && clickCount == 1){
			winner = entry2[0];
			option();
			btn.setText("WINNER");
		}
		
		clickCount++;
		
		if(ROUND > 2){
			btn.setText(ROUND + " R");
		} else if( ROUND == 2){
			btn.setText("FINAL");
		}
		
	}
	
	public void Right(){
		//32강
		System.out.println();
		if(roundCount == 1){
			for(int i = 0; i < 16; i++){ // 16 경기
				if(clickCount == i+1){
					if(clickCount < i) break;
					
					entry16[i] = img[j+1].getImgName();
					img[j].setImgName("");
					j += 2;
					
					if(j > 30){ // 32강 마지막경기 이후
						option();
						break;
					} 
					
					imgLeft.setImgName(img[j].getImgName());
					imgRight.setImgName(img[j+1].getImgName());
				}//end if
			}//end for
		}// end 32강
		
		//16강
		else if (roundCount == 2){
			for(int i = 0; i < 8; i++){ // 8경기
				if(clickCount == i+1){
					if(clickCount < i) break;
					
					entry8[i] = entry16[j+1];
					for(int q = 0; q < 32; q++){
						if(entry16[j] == img[q].getImgName()){
							img[q].setImgName("");
							break;
						}
					}
					
					j += 2;
					if(j > 14){ // 16강 마지막경기 이후 
						option();
						break;
					}
					
					imgLeft.setImgName(entry16[j]);
					imgRight.setImgName(entry16[j+1]);
				}
			}//end for
		}// end 16
		
		// 8강
		else if(roundCount == 3){
			for(int i = 0; i < 4; i++){
				if(clickCount == i+1){
					if(clickCount < i) break;
					
					entry4[i] = entry8[j+1];
					for(int q = 0; q < 32; q++){
						if(entry8[j] == img[q].getImgName()){
							img[q].setImgName("");
							break;
						}
					}
					
					j += 2;
					if(j > 6){
						option();
						break;
					}
					
					imgLeft.setImgName(entry8[j]);
					imgRight.setImgName(entry8[j+1]);
				}
			}
		} // end 8
		
		// 4강
		else if(roundCount == 4 && clickCount == 1){
			entry2[0] = entry4[1];
			imgLeft.setImgName(entry4[2]);
			imgRight.setImgName(entry4[3]);
		} else if(roundCount == 4 && clickCount == 2){
			entry2[1] = entry4[3];
			option();
		}
		
		else if(roundCount == 5 && clickCount == 1){
			winner = entry2[1];
			option();
			btn.setText("WINNER");
		}
		
		clickCount++;
		
			if(ROUND > 2){
					btn.setText(ROUND + " R");
			} else if( ROUND == 2){
					btn.setText("FINAL");
			}
			
	}	
	
	
	
	
	
}