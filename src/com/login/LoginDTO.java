package com.login;
import java.util.ArrayList;

public class LoginDTO {
	String ID, 비밀번호, 이름, 전화번호;
	
	static ArrayList<LoginDTO> list = new ArrayList<LoginDTO>();
	
	LoginDTO (String ID, String 비밀번호, String 이름, String 전화번호)  {
		this.ID = ID;
		this.비밀번호 = 비밀번호;
		this.이름 = 이름;
		this.전화번호 = 전화번호;
	
	
	}

}
