package com.dog;
import java.util.ArrayList;

public class DogDTO {
	String 품종, 국적, 크기, 털빠짐, 성격, 분양가;
	
	static ArrayList<DogDTO> list = new ArrayList<DogDTO>();
	
	DogDTO (String 품종, String 국적, String 크기, String 털빠짐, String 성격, String 분양가)  {
		this.품종 = 품종;
		this.국적 = 국적;
		this.크기 = 크기;
		this.털빠짐 = 털빠짐;
		this.성격 = 성격;
		this.분양가 = 분양가;
	}

}
