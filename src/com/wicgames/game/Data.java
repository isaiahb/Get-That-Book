package com.wicgames.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	public static Data config;
	public static Data save;
	private ArrayList<keypair> data;
	private Data(String path) throws IOException{
		data = new ArrayList<keypair>();
		BufferedReader dataFile = null;
		try {
			dataFile = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(dataFile.ready()){
			String[] entry = dataFile.readLine().split(":");
			data.add(new keypair(entry[0],entry[1]));
		}
	}
	private class keypair{
		public String name;
		public String value;
		public keypair(String name,String value){
			this.name = name;
			this.value = value;
		}
	}
}
