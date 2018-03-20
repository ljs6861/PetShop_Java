package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.db.DbConn;
import com.main.HomeGUI;

public abstract class LoginDAO {
	private static DefaultTableModel model;
	static PreparedStatement pSt ;
	LoginGUI login = new LoginGUI();

	
	// ------------------------------------------------------------
	//회원가입
	public static void LoginInsert(String str1, String str2, String str3, String str4) {
		

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD);
			stmt = conn.createStatement();

			String queryLang = "insert into logintbl (ID, 비밀번호, 이름, 전화번호) values('" + str1 + "', '" + str2 + "', '"
					+ str3 + "', '" + str4 + "');";
			stmt.executeUpdate(queryLang);
			System.out.println("새로운 회원 등록완료.");

	

			
			
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (Exception a) {
			}
			try {
				conn.close();
			} catch (Exception a) {
			}
		}
	}

	// ------------------------------------------------------------
	// ID,비밀번호 인증	
	public static int LoginSearch(String ID, String 비밀번호) { 
															
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String dbpasswd = "";
		int x = -1;

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD);
			sql = "select 비밀번호 from logintbl where ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {  //아이디가 있는경우

				dbpasswd = rs.getString("비밀번호");
				if (dbpasswd.equals(비밀번호)) {
					x = 1; // 인증성공
								
				} 
				else {

					x = 0; // 비밀번호 틀림
				}

			} 
			
			else {
				x = -1; // 해당 아이디 없음
			
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			execClose(rs, pstmt, conn);
		}
		return x;
	}

	

	private static void execClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		// TODO Auto-generated method stub

	}

	

	public static void printTable(JTable table) {
		// TODO Auto-generated method stub

	}

}
