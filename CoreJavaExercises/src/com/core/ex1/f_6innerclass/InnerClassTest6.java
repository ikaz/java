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
 * @author icastillejos
 * @version 1.0.0
 */
public class InnerClassTest6 {
	public static void main(String[] args){
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start2();
		
		//keep program running until user selects "Ok"
		JOptionPane.showMessageDialog(null,"Quit program?");
		System.exit(0);		
	}
}

/**
 * @author icastillejos
 * @version 1.0.0
 */
class TalkingClock{
	private int interval;
	private boolean beep;
	
	/**
	 * Constructs a talking clock
	 * @param interval
	 * @param beep
	 */
	public TalkingClock(int interval, boolean beep){
		this.interval = interval;
		this.beep = beep;
	}
	
	/**
	 * Starts the clock...
	 * */
	public void start(){
		//ActionListener listener = this.new TimePrinter(this);
		ActionListener listener = new TimePrinter(this);
		Timer t = new Timer(interval, listener);
		t.start();
	}
	
	/**
	 * Start 2
	 * */
	public void start2(){
		
		class TimePrinter2 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("2::: At the tone, the time is " + now + " --- "+ interval);
				
				//if (TalkingClock.this.beep){
				if (beep){
					Toolkit.getDefaultToolkit().beep();
				}
			}
			
		}
		
		ActionListener listener = new TimePrinter2();
		Timer t = new Timer(interval, listener);
		t.start();
		
		
	}
	
	/** start3 */
	public void start3(int interval, final boolean beep){
		ActionListener listener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
				System.out.println("At the tone the time is " + d);
				if (beep){
					Toolkit.getDefaultToolkit().beep();
				}
			}	
		};
		Timer t = new Timer(interval, listener);
		t.start();
	}
	
	public class TimePrinter implements ActionListener{
		public TalkingClock outer;
		
		public TimePrinter(TalkingClock clock){
			outer = clock;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Date now = new Date();
			System.out.println("At the tone, the time is " + now + " --- "+ interval);
			
			//if (TalkingClock.this.beep){
			if (outer.beep){
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}