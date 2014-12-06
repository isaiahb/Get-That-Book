package com.wicgames.wicLibrary;

import java.util.ArrayList;

public class Event {
	private ArrayList<Function> functions = new ArrayList<Function>();
	public void trigger() {
		for (int i = 0; i < functions.size(); i++) 
			 functions.get(i).call();
	}
	public <T> void trigger(T t) {
		for (int i = 0; i < functions.size(); i++) 
			 functions.get(i).call(t);
	}	
	public void connect(Function function) {
		functions.add(function);
	}
	public void disconnect(Function function){
		functions.remove(function);
	}
}
