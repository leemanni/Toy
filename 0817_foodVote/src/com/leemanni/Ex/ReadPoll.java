package com.leemanni.Ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadPoll {
	public static ArrayList<String> readPoll(String filePath){
		ArrayList<String> pollList = null;
		Scanner scanner = null;
		
		try {
			File file = new File(filePath);
			scanner = new Scanner(file);
			pollList = new ArrayList<>();
			while (scanner.hasNext()) {
				pollList.add(scanner.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않음");
			e.printStackTrace();
		}
		
		return pollList;
	}
}
