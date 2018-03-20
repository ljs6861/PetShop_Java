package com.shop;

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

public class ShopEXCEL {

	public ShopEXCEL () throws IOException {
		// EXCEL - API(POI) : http://www.apache.org
		//http://mvnrepository.com/search?q=poi  여기서 poi 3.1 다운받고 build path 추가하면됨
		// 1. EXCEL 파일 만들기

		
		
		
		
		Connection conn = null;
		Statement stmt = null;
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		// Sheet
		HSSFSheet sheet = wb.createSheet("shoplist");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;

		
		
		
		FileOutputStream fos = new FileOutputStream("shoplist.xls",true);

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
				
			
				
				
				cell = row.createCell(0);
				cell.setCellValue(애견샵);
				
				cell = row.createCell(1);
				cell.setCellValue(지역);
				
				cell = row.createCell(2);
				cell.setCellValue(주소);
				
				cell = row.createCell(3);
				cell.setCellValue(연락처);
				
				cell = row.createCell(4);
				cell.setCellValue(사이트);
				
				
				
				int rowidx = sheet.getLastRowNum();
				row = sheet.createRow(rowidx + 1);
				
				
			}
			
		
			
			wb.write(fos);
			fos.close();
			

			System.out.println("엑셀 저장완료 (애견샵)");
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
