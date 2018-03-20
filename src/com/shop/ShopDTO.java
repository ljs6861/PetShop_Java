package com.shop;
import java.util.ArrayList;

public class ShopDTO {
	String 애견샵, 지역, 주소, 연락처, 사이트, 분양가;
	
	static ArrayList<ShopDTO> list = new ArrayList<ShopDTO>();
	
	ShopDTO (String 애견샵, String 지역, String 주소, String 연락처, String 사이트)  {
		this.애견샵 = 애견샵;
		this.지역 = 지역;
		this.주소 = 주소;
		this.연락처 = 연락처;
		this.사이트 = 사이트;
	
	}

}
