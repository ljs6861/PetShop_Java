package com.tour;

import java.util.ArrayList;

public class DogVO {

	private String index;
	private String dogName;
	private String imgName;
	
	public DogVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DogVO(String index, String dogName, String imgName) {
		super();
		this.index = index;
		this.dogName = dogName;
		this.imgName = imgName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDogName() {
		return dogName;
	}
	public void setDogName(String dogName) {
		this.dogName = dogName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	@Override
	public String toString() {
		return "DogVO [index=" + index + ", dogName=" + dogName + ", imgName=" + imgName + "]";
	}
	
	
}
