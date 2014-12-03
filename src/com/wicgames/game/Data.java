package com.wicgames.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	public static Data config;
	public static Data save = new Data("bin/assets/data/save/save");
	private ArrayList<keypair> data;
	private Data(String path){
		data = new ArrayList<keypair>();
		BufferedReader dataFile = null;
		try {
			dataFile = new BufferedReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while(dataFile.ready()){
				String[] entry = dataFile.readLine().split(":");
				data.add(new keypair(entry[0],entry[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getValue(String key){
		for(keypair k : data)
			if(k.name.equals(key))
				return k.value;
		return "Unknown keypair";
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
