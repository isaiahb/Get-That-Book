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
	public <T> void trigger(T a, T b) {
		for (int i = 0; i < functions.size(); i++) 
			 functions.get(i).call(a, b);
	}
	public <T> void trigger(T a, T b, T c) {
		for (int i = 0; i < functions.size(); i++) 
			 functions.get(i).call(a, b, c);
	}
	public <T> void trigger(T a, T b, T c, T d) {
		for (int i = 0; i < functions.size(); i++) 
			 functions.get(i).call(a, b, c, d);
	}
	
	public void connect(Function function) {
		functions.add(function);
	}
	public void disconnect(Function function){
		functions.remove(function);
	}
}
