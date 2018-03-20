package com.shop;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import com.dog.DogGUI;
import com.db.DbConn;

public abstract class ShopDAO {
	private static DefaultTableModel model;

	// 애견샵 관리기능 클래스
	
	// ------------------------------------------------------------
	// DB에 애견샵 저장
	public static void ShopInsert(String str1, String str2, String str3, String str4, String str5) { 
		
	
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD);
			stmt = conn.createStatement();
	
			
			String queryLang = "insert into Shoptbl (애견샵, 지역, 주소, 연락처, 사이트) values('" + str1 + "', '" + str2 + "', '"
					+ str3 + "', '" + str4 + "','" + str5 + "');";
			int rowNum = stmt.executeUpdate(queryLang);
			System.out.println("새로운 샵 등록완료.");
			JOptionPane.showMessageDialog(null,str1+ "이(가) 추가 되었습니다.");
			
			
			ShopDTO.list.add(new ShopDTO(str1, str2, str3, str4, str5));
			String rowData[] = { str1, str2, str3, str4, str5 };
			ShopGUI.model.addRow(rowData);

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
	// DB에 저장되어 있는 애견샵 삭제 메소드
	public static void ShopDelete(String codeStr) { 
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select *from Shoptbl where 애견샵 = '" + codeStr + "';");
			String name = null;

			while (rs.next()) {
				name = rs.getString("애견샵"); // ****
			}
			if (name == null) {
				System.out.println("해당 코드의 샵이 없습니다");
				return;
			}
			String queryLang = "delete from Shoptbl where 애견샵 = '" + codeStr + "';";
			int rowNum = stmt.executeUpdate(queryLang);

			JOptionPane.showMessageDialog(null,name+ "이(가) 삭제 되었습니다.");
		
			for (int cnt = 0; cnt < ShopDTO.list.size(); cnt++) { // 자료구조에 저장되어
																	// 있는
																	// 애견샵 데이터
																	// 삭제
				ShopDTO obj = ShopDTO.list.get(cnt);
				if (obj.애견샵.equals(codeStr)) {
					ShopDTO.list.remove(cnt);
					ShopGUI.model.removeRow(cnt);
					break;
				}
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	
	// ------------------------------------------------------------
	// 모든 애견샵 데이터 테이블 튜플에 추가
		public static void ShopSelect(DefaultTableModel model) { 
																	

			allRemoveRowData(model);

			Connection conn = null;
			Statement stmt = null;
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager.getConnection(DbConn.DB_URL,
						DbConn.DB_User,
						DbConn.DB_PASSWD);
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Shoptbl;");

				String 애견샵, 지역, 주소, 연락처, 사이트;
				while (rs.next()) {
					애견샵 = rs.getString("애견샵");
					지역 = rs.getString("지역");
					주소 = rs.getString("주소");
					연락처 = rs.getString("연락처");
					사이트 = rs.getString("사이트");

					ShopDTO.list.add(new ShopDTO(애견샵, 지역, 주소, 연락처, 사이트));
					String str[] = { 애견샵, 지역, 주소, 연락처, 사이트 };
					model.addRow(str);
				}

			} catch (ClassNotFoundException cnfe) {
				System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			} finally {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				try {
					conn.close();
				} catch (Exception e) {
				}
			}

		}
	

	// ------------------------------------------------------------
	// 애견샵 검색기능
	public static void ShopSearch(String 애견샵, String 주소, String 연락처, JTable table) { 
																						

		if (애견샵.equals("") && 주소.equals("") && 연락처.equals("")) {
			System.out.println("검색조건을 한개 이상 입력해주세요");
			return;
		}
		int size = ShopDTO.list.size();
		String tempData1[][] = new String[size][6];
		String tempData2[][] = new String[size][6];
		int cntData1 = 0;
		int cntData2 = 0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		if (!(애견샵.equals(""))) {
			for (ShopDTO obj : ShopDTO.list) {
				if (!(-1 == obj.애견샵.indexOf(애견샵))) {
					tempData1[cntData1][0] = obj.애견샵;
					tempData1[cntData1][1] = obj.지역;
					tempData1[cntData1][2] = obj.주소;
					tempData1[cntData1][3] = obj.연락처;
					tempData1[cntData1][4] = obj.사이트;

					cntData1++;
				}
			}
			if (tempData1[0][0] == null) {
				System.out.println("검색한 샵 정보가 없습니다.");
				return;
			}
		}

		// -----------------------------------------
		if (!(주소.equals(""))) {
			if (tempData1[0][0] == null) {
				for (ShopDTO obj : ShopDTO.list) {
					if (!(-1 == obj.주소.indexOf(주소))) {
						tempData1[cntData1][0] = obj.애견샵;
						tempData1[cntData1][1] = obj.지역;
						tempData1[cntData1][2] = obj.주소;
						tempData1[cntData1][3] = obj.연락처;
						tempData1[cntData1][4] = obj.사이트;

						cntData1++;
					}
				}
				if (tempData1[0][0] == null) {
					System.out.println("검색한 샵정보가 없습니다.");
					return;
				}
			} else {
				for (int cnt = 0; cnt < cntData1; cnt++) {
					if (!(-1 == tempData1[cnt][2].indexOf(주소))) {
						tempData2[cntData2] = tempData1[cnt];
						cntData2++;
					}
				}
				if (tempData2[0][0] == null) {
					System.out.println("검색한 샵 정보가 없습니다.");
					return;
				}
			}
		}
		// -------------------------------------------------------------------
		if (!(연락처.equals(""))) {
			if (tempData2[0][0] == null) {
				if (tempData1[0][0] == null) {
					int count = 0;
					for (ShopDTO obj : ShopDTO.list) {
						if (!(-1 == obj.연락처.indexOf(연락처))) {
							String arr[] = { obj.애견샵, obj.지역, obj.주소, obj.연락처, obj.사이트 };
							model.addRow(arr);
						}
					}

				} else {
					int count = 0;
					for (int cnt = 0; cnt < cntData1; cnt++) {
						if (!(-1 == tempData1[cnt][3].indexOf(연락처))) {
							model.addRow(tempData1[cnt]);
							count++;
						}
					}
					if (count == 0)
						System.out.println("검색한 샵 정보가 없습니다.");
				}
			} else {
				for (int cnt = 0; cnt < cntData2; cnt++) {
					if (!(-1 == tempData2[cnt][3].indexOf(연락처)))
						model.addRow(tempData2[cnt]);
				}
			}
		} else {
			if (tempData2[0][0] == null) { // 애견샵 or 주소만 입력했을시
				for (int cnt = 0; cnt < cntData1; cnt++) {
					if (tempData1[cnt][0] == null)
						return;
					model.addRow(tempData1[cnt]);
				}
			} else { // 애견샵 and 주소 입력했을시
				for (int cnt = 0; cnt < cntData2; cnt++) {
					if (tempData2[cnt][0] == null)
						return;
					model.addRow(tempData2[cnt]);
				}
			}
		}
	}

	// ------------------------------------------------------------
	// 선택한 테이블 행의 데이터를 가져오는
	public static String[] getTableData(JTable table) { 
														
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowNum = table.getSelectedRow();
		if (rowNum == -1)
			return null;
		int colCount = table.getColumnCount();
		String tableData[] = new String[colCount];
		for (int cnt = 0; cnt < colCount; cnt++) {
			Object obj = model.getValueAt(rowNum, cnt);
			tableData[cnt] = obj.toString();
		}
		return tableData;
	}

	// ------------------------------------------------------------
	// 테이블의 모든 튜플 삭제
	public static void allRemoveRowData(DefaultTableModel model) { 
																
		int rowCount = model.getRowCount();

		for (int cnt = 0; cnt < rowCount; cnt++) {
			model.removeRow(0);

		}

	}

	

	public static void printTable(JTable table) {
		// TODO Auto-generated method stub

	}

}
