/**
 * 
 */
package com.core.ex1.f_6innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Demonstrates anonymus innner classes
 * @author icastillejos
 * @version 1.0.0
 */
public class AnonymousInnerClassTest7 {
	public static void main(String[] args){
		TalkingClockIN clock = new TalkingClockIN();
		clock.start(1000, true);
		//keep running until clicked
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}

/**
 * Clock that prints the time in regular intervals
 */
class TalkingClockIN{
	
	public void start(int interval, final boolean beep){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("At the tone, the time is "+now);
				if (beep) Toolkit.getDefaultToolkit().beep();
			}
		};
		Timer t = new Timer(interval, listener);
		t.start();
	}
}