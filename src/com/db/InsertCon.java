package com.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.login.LoginGUI;

public class InsertCon {

public static void main(String[] args) {

		
		

		Connection conn = null;
		PreparedStatement pSt1 = null;
		PreparedStatement pSt2 = null;
		
		String doglist = "src/data/doglist.txt";
		String shoplist = "src/data/shoplist.txt";
		
		FileReader fileReader1 = null;
		FileReader fileReader2 = null;
		BufferedReader buffer1 = null;
		BufferedReader buffer2 = null;
		
		try {
//			 names 파일을 읽기 위한 준비
			fileReader1 = new FileReader(doglist);
			buffer1 = new BufferedReader(fileReader1);
			
			fileReader2 = new FileReader(shoplist);
			buffer2 = new BufferedReader(fileReader2);
			
			// DB 연결을 위한 준비
			Class.forName(DbConn.DB_Driver);
			System.out.println(DbConn.Msg.Msg_Conn_Success);
			
			conn = DriverManager.getConnection(
					DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD
					);
			
			String reader1 = new String();
			String reader2 = new String();
		
			String sql1 = " INSERT INTO dogtbl values(?,?,?,?,?,?); " ;
			String sql2 = " INSERT INTO shoptbl values(?,?,?,?,?); " ;
			pSt1 = conn.prepareStatement(sql1); // sql을 서버로 전송
			pSt2 = conn.prepareStatement(sql2);
			while(true){
				reader1 = buffer1.readLine(); // 파일에A서 한줄씩 읽기
				if(reader1 == null) break;
				// 0:품종, 1:국적, 2:크기, 3:털빠짐, 4:성격, 5:분양가
				String[] dognames = reader1.split(":");
				pSt1.setString(1, dognames[0]);
				pSt1.setString(2, dognames[1]);
				pSt1.setString(3, dognames[2]);
				pSt1.setString(4, dognames[3]);
				pSt1.setString(5, dognames[4]);
				pSt1.setString(6, dognames[5]);
				
				pSt1.executeUpdate();
				
				
				reader2 = buffer2.readLine(); // 파일에서 한줄씩 읽기
				if(reader2 == null) break;
				// 0:애견샵, 1:지역, 2:주소, 3:전화번호, 4:사이트
				String[] shopnames = reader2.split(":");
				pSt2.setString(1, shopnames[0]);
				pSt2.setString(2, shopnames[1]);
				pSt2.setString(3, shopnames[2]);
				pSt2.setString(4, shopnames[3]);
				pSt2.setString(5, shopnames[4]);
				
				pSt2.executeUpdate();
				
			}
			buffer1.close();
			fileReader1.close();
			buffer2.close();
			fileReader2.close();
			
			System.out.println(DbConn.Msg.Msg_DbInsert_Sucess);
		
		
	
	
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}