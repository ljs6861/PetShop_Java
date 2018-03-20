package com.dog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.db.DbConn;

public class DogEXCEL {

	public DogEXCEL () throws IOException {
		// EXCEL - API(POI) : http://www.apache.org
		//http://mvnrepository.com/search?q=poi  여기서 poi 3.1 다운받고 build path 추가하면됨
		// 1. EXCEL 파일 만들기

		
		
		
		
		Connection conn = null;
		Statement stmt = null;
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		// Sheet
		HSSFSheet sheet = wb.createSheet("doglist");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;

		
		
		
		FileOutputStream fos = new FileOutputStream("doglist.xls",true);

		try {

		
			
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection(DbConn.DB_URL,
					DbConn.DB_User,
					DbConn.DB_PASSWD);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from dogtbl;");
			
		
			
			String 품종, 국적, 크기, 털빠짐, 성격, 분양가;
			while (rs.next()) {
				
				
				
				
				품종 = rs.getString("품종");
				국적 = rs.getString("국적");
				크기 = rs.getString("크기");
				털빠짐 = rs.getString("털빠짐");
				성격 = rs.getString("성격");
				분양가 = rs.getString("분양가");
				
			
				
				
				cell = row.createCell(0);
				cell.setCellValue(품종);
				
				cell = row.createCell(1);
				cell.setCellValue(국적);
				
				cell = row.createCell(2);
				cell.setCellValue(크기);
				
				cell = row.createCell(3);
				cell.setCellValue(털빠짐);
				
				cell = row.createCell(4);
				cell.setCellValue(성격);
				
				cell = row.createCell(5);
				cell.setCellValue(분양가);
				
				int rowidx = sheet.getLastRowNum();
				row = sheet.createRow(rowidx + 1);
				
				
			}
			
		
			
			wb.write(fos);
			fos.close();
			

			System.out.println("엑셀 저장완료 (애완견)");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
