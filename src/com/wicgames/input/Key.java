package com.wicgames.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.wicgames.game.Main;
import com.wicgames.wicLibrary.Event;

public abstract class Key {
	public static Event typed = new Event();
	public static Event typedEvent = new Event();
	public static Event[] pressed = new Event[127];
	public static Event[] released = new Event[127];
	public static boolean[] down = new boolean[127];

	public static void init() {
		for (int i = 0; i < pressed.length; i++) {
			pressed[i] = new Event();
			released[i] = new Event();
		}
		Main.panel.addKeyListener(listener);
	}

	public static KeyListener listener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent event) {
			int code = event.getKeyCode();
			if (!down[code]) {
				pressed[code].trigger();
				typedEvent.trigger(event);
			}
			down[code] = true;

		}

		@Override
		public void keyReleased(KeyEvent event) {
			int code = event.getKeyCode();
			released[event.getKeyCode()].trigger();
			down[code] = false;
		}

		@Override
		public void keyTyped(KeyEvent event) {
			typed.trigger(event.getKeyChar());
		}
	};
}
