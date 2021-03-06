package com.core.ex1.j_10deploy;
// Had to make the folder structure match the package, easiest way to test it was remoing the package name


import java.awt.EventQueue;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ResourceTest1 {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = new ResourceTestFrame();
				frame.setTitle("Resource Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame that loads image and text resources
 */
class ResourceTestFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	public ResourceTestFrame(){
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		URL aboutURL = getClass().getResource("about.gif");
		Image img = new ImageIcon(aboutURL).getImage();
		this.setIconImage(img);
		
		JTextArea textArea = new JTextArea();
		InputStream stream = getClass().getResourceAsStream("about.txt");
		Scanner in = new Scanner(stream);
		while (in.hasNext()){
			textArea.append(in.nextLine()+"\n");
		}		
		this.add(textArea);
	}
}