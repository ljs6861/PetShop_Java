package com.dog;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import com.shop.ShopGUI;
import com.tour.WinnerView;
import com.db.DbConn;
import com.shop.ShopDTO;

public abstract class DogDAO {
	private static DefaultTableModel model;

	// 애완견 관리기능 클래스
	// ------------------------------------------------------------
	// DB에 애완견 저장
	public static void DogInsert(String str1, String str2, String str3, String str4, String str5, String str6) {

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_PASSWD);
			stmt = conn.createStatement();

			String queryLang = "insert into dogtbl (품종, 국적, 크기, 털빠짐, 성격, 분양가) values('" + str1 + "', '" + str2 + "', '"
					+ str3 + "', '" + str4 + "','" + str5 + "','" + str6 + "');";
			int rowNum = stmt.executeUpdate(queryLang);
			JOptionPane.showMessageDialog(null, str1 + "이(가) 추가되었습니다.");
			System.out.println("새로운 애완견 등록완료.");

			DogDTO.list.add(new DogDTO(str1, str2, str3, str4, str5, str6));
			String rowData[] = { str1, str2, str3, str4, str5, str6 };
			DogGUI.model.addRow(rowData);

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
	// DB에 저장되어 있는 애완견 삭제
	public static void DogDelete(String codeStr, String win) {

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_PASSWD);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select *from dogtbl where 품종 = '" + codeStr + "';");
			String name = null;
			while (rs.next()) {
				name = rs.getString("품종"); // ****
			}
			if (name == null) {
				System.out.println("해당 코드의 애완견이 없습니다");
				return;
			}
			String queryLang = "delete from dogtbl where 품종 = '" + codeStr + "';";
			int rowNum = stmt.executeUpdate(queryLang);

			JOptionPane.showMessageDialog(null, name + "이(가) 삭제되었습니다.");

			
			// 자료구조에 저장되있는 애완견 데이터 삭제
			if (!(win == null)) { //우승자가 갱신된 테이블일 경우
				for (int cnt = 0; cnt < DogDTO.list.size(); cnt++) { 
																
					DogDTO obj = DogDTO.list.get(cnt);
					if (obj.품종.equals(codeStr)) {
						DogDTO.list.remove(cnt);
						DogGUI.model.removeRow(cnt-1); //우승자가 addRow로 추가되었기때문에 -1 (테이블)
						break;
					}

				}
			} else {	//우승자가 갱신안된 테이블일경우
				for (int cnt = 0; cnt < DogDTO.list.size(); cnt++) { 
																		

					DogDTO obj = DogDTO.list.get(cnt);
					if (obj.품종.equals(codeStr)) {
						DogDTO.list.remove(cnt);
						DogGUI.model.removeRow(cnt);
						break;
					}
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
	// 모든 애완견 데이터 테이블 튜플에 추가
	public static void DogSelect(DefaultTableModel model) {

		allRemoveRowData(model);

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_PASSWD);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select *from dogtbl;");

			String 품종, 국적, 크기, 털빠짐, 성격, 분양가;
			while (rs.next()) {
				품종 = rs.getString("품종");
				국적 = rs.getString("국적");
				크기 = rs.getString("크기");
				털빠짐 = rs.getString("털빠짐");
				성격 = rs.getString("성격");
				분양가 = rs.getString("분양가");

			
				DogDTO.list.add(new DogDTO(품종, 국적, 크기, 털빠짐, 성격, 분양가));
				String str[] = { 품종, 국적, 크기, 털빠짐, 성격, 분양가 };
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
	// 애완견 검색 기능
	public static void DogSearch(String 품종, String 크기, String 털빠짐, JTable table) {

		if (품종.equals("") && 크기.equals("") && 털빠짐.equals("")) {
			System.out.println("검색조건을 한개 이상 입력해주세요");
			return;
		}
		int size = DogDTO.list.size();
		String tempData1[][] = new String[size][6];
		String tempData2[][] = new String[size][6];
		int cntData1 = 0;
		int cntData2 = 0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// ------------------------------------------------------------
		if (!(품종.equals(""))) {
			for (DogDTO obj : DogDTO.list) {
				if (!(-1 == obj.품종.indexOf(품종))) {
					tempData1[cntData1][0] = obj.품종;
					tempData1[cntData1][1] = obj.국적;
					tempData1[cntData1][2] = obj.크기;
					tempData1[cntData1][3] = obj.털빠짐;
					tempData1[cntData1][4] = obj.성격;
					tempData1[cntData1][5] = obj.분양가;
					cntData1++;
				}
			}
			if (tempData1[0][0] == null) {
				System.out.println("검색한 애완견 정보가 없습니다.");
				return;
			}
		}

		// -----------------------------------------
		if (!(크기.equals(""))) {
			if (tempData1[0][0] == null) {
				for (DogDTO obj : DogDTO.list) {
					if (!(-1 == obj.크기.indexOf(크기))) {
						tempData1[cntData1][0] = obj.품종;
						tempData1[cntData1][1] = obj.국적;
						tempData1[cntData1][2] = obj.크기;
						tempData1[cntData1][3] = obj.털빠짐;
						tempData1[cntData1][4] = obj.성격;
						tempData1[cntData1][5] = obj.분양가;

						cntData1++;
					}
				}
				if (tempData1[0][0] == null) {
					System.out.println("검색한 애완견정보가 없습니다.");
					return;
				}
			} else {
				for (int cnt = 0; cnt < cntData1; cnt++) {
					if (!(-1 == tempData1[cnt][2].indexOf(크기))) {
						tempData2[cntData2] = tempData1[cnt];
						cntData2++;
					}
				}
				if (tempData2[0][0] == null) {
					System.out.println("검색한 애완견 정보가 없습니다.");
					return;
				}
			}
		}
		// -------------------------------------------------------------------
		if (!(털빠짐.equals(""))) {
			if (tempData2[0][0] == null) {
				if (tempData1[0][0] == null) {
					int count = 0;
					for (DogDTO obj : DogDTO.list) {
						if (!(-1 == obj.털빠짐.indexOf(털빠짐))) {
							String arr[] = { obj.품종, obj.국적, obj.크기, obj.털빠짐, obj.성격, obj.분양가 };
							model.addRow(arr);
						}
					}

				} else {
					int count = 0;
					for (int cnt = 0; cnt < cntData1; cnt++) {
						if (!(-1 == tempData1[cnt][3].indexOf(털빠짐))) {
							model.addRow(tempData1[cnt]);
							count++;
						}
					}
					if (count == 0)
						System.out.println("검색한 애완견 정보가 없습니다.");
				}
			} else {
				for (int cnt = 0; cnt < cntData2; cnt++) {
					if (!(-1 == tempData2[cnt][3].indexOf(털빠짐)))
						model.addRow(tempData2[cnt]);
				}
			}
		} else {
			if (tempData2[0][0] == null) { // 품종 or 크기만 입력했을시
				for (int cnt = 0; cnt < cntData1; cnt++) {
					if (tempData1[cnt][0] == null)
						return;
					model.addRow(tempData1[cnt]);
				}
			} else { // 품종 and 크기 입력했을시
				for (int cnt = 0; cnt < cntData2; cnt++) {
					if (tempData2[cnt][0] == null)
						return;
					model.addRow(tempData2[cnt]);
				}
			}
		}
	}

	// ------------------------------------------------------------
	// 우승자 데이터만 리스트에 추가
	public static void DogSelect2(DefaultTableModel model, String win) {

		allRemoveRowData(model);

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL, DbConn.DB_User, DbConn.DB_PASSWD);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dogtbl where 품종 = '" + win + "';");

			String 품종, 국적, 크기, 털빠짐, 성격, 분양가;
			while (rs.next()) {
				품종 = rs.getString("품종");
				국적 = rs.getString("국적");
				크기 = rs.getString("크기");
				털빠짐 = rs.getString("털빠짐");
				성격 = rs.getString("성격");
				분양가 = rs.getString("분양가");
				DogDTO.list.add(new DogDTO(품종, 국적, 크기, 털빠짐, 성격, 분양가));
				String str[] = { 품종, 국적, 크기, 털빠짐, 성격, 분양가 };
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
	// 선택한 테이블 행의 데이터를 가져옴
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
