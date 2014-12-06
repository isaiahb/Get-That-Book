package com.wicgames.wicLibrary;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip sound;
	public Sound(String path){
		try{
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(path)))));
		}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e){
			e.printStackTrace();
		}
	}
	public Sound(InputStream in){
		try{
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(new BufferedInputStream(in)));
		}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e){
			e.printStackTrace();
		}
	}
	public void startSound(){
		sound.start();
	}
	public void stopSound(){
		sound.stop();
	}
	public void loopSound(int loopCount){
		sound.loop(loopCount);
	}
}
