package com.wicgames.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Data {
	public static Data config = new Data("bin/assets/data/config/config");
	public static Data save = new Data("bin/assets/data/save/save");
	private ArrayList<keypair> data;
	public Data(String path){
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
		return null;
	}
	/**
	 * Set value of a key
	 * @param key Name of key being changed
	 * @param value New Value for key
	 * @return True if key already exists, False if key does not.
	 */
	public boolean setValue(String key,String value){
		for(keypair k : data)
			if(k.name.equals(key)){
				k.value = value;
				return true;
			}
		data.add(new keypair(key,value));
		return false;
				
	}
	/**
	 * Saves data in data array to file
	 * @param path Path of file to be saved in
	 * @throws IOException FileWriter throws this
	 */
	public void saveData(String path) throws IOException{
		PrintWriter dataWriter = new PrintWriter(new FileWriter(path));
		for(keypair k : data)
			dataWriter.println(k.name + ":" + k.value);
		dataWriter.close();
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
