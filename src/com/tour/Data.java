package com.tour;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.dog.DogDAO;


public class Data {
	public DogVO vo = new DogVO();
	private static ArrayList<DogVO> dogs = new ArrayList<DogVO>();

	public void loadData() {

		String filename = "src/data/data.txt";

		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(fileReader);
			String reader = new String();

			while (true) {
				// ArrayList dogs 에 index, dogName, imgName 값 저장.
				reader = buffer.readLine();
				if (reader == null)
					break;

				String[] read = reader.split(":");
				// index dogName imgName
				DogVO vo = new DogVO(read[0], read[1], read[2]);
				
				if(dogs.size() <32){
				dogs.add(vo);
				}
				
				
			}
			
			
			
			
			// 대진표 섞기.
			Collections.shuffle(dogs);
			buffer.close();
			fileReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("데이터로드실패");
		}
	}

	public ArrayList<DogVO> getDogs() {
		return dogs;
	}

	public void setDogs(ArrayList<DogVO> dogs) {
		this.dogs = dogs;
	}

	// 받은 이미지의 개이름 리턴.
	public String printName(String _imgName) {
		String name = "";
		for (int i = 0; i < 32; i++) {
			if (dogs.get(i).getImgName().equals(_imgName)) {
				name = dogs.get(i).getDogName();
				break;
			}
		}
		return name;
	}

}
