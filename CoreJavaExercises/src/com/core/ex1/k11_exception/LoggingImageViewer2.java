/**
 * 
 */
package com.core.ex1.k11_exception;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

/**
 * @author icastillejos
 * @version 1.0.01
 */
public class LoggingImageViewer2 {
	public static void main(String[] args){
		if(System.getProperty("java.util.logging.config.class") == null && 
		   System.getProperty("java.util.logging.config.file") == null)	{
			try {
				Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%h/%gLoggingImageViewer.log",0,LOG_ROTATION_COUNT);
				Logger.getLogger("com.horstmann.corejava").addHandler(handler);
			} catch (IOException e){
				Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, "Can't create a log file handler", e);
			}
		}
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				Handler windowHandler = new WindowHandler();
				windowHandler.setLevel(Level.ALL);
				Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);
				
				JFrame frame = new ImageViewerFrame();
				frame.setTitle("LoggingImageViewer");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Logger.getLogger("com.horstmann.corejava").fine("Showing frame");
				frame.setVisible(true);
				
			}			
		});
	}
}

/**
 * The frame that shows the image
 */
class ImageViewerFrame extends JFrame{ 
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;

	private JLabel label;
	private static Logger logger = Logger.getLogger("com.horstmann.corejava");
	
	public ImageViewerFrame(){
		logger.entering("ImageViewerFrame", "<init>");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.fine("Exiting");
				System.exit(0);
			}
		});
		//use a label to display the images
		label = new JLabel();
		this.add(label);
		logger.exiting("ImageViewerFrame", "<init>");
	}
	

	/**
	 * File Open Listener
	 * */
	private class FileOpenListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPeformed", event);
			//setup file chooser
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			//accept gif files
			chooser.setFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "GIF imagenessss";
				}
				
			});
			//show file chooser dialog
			int r = chooser.showOpenDialog(ImageViewerFrame.this);
			//if img accepted set label icon
			if (r == JFileChooser.APPROVE_OPTION){
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file {0}", name);
				label.setIcon(new ImageIcon(name));
			} else {
				logger.fine("File open dialog canceled.");
				logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
			}				
		}
	}
}

/**
 * Handler for displaying log records in a window.
 */
class WindowHandler extends StreamHandler{
	private JFrame frame;
	
	public WindowHandler(){
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(400, 200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		setOutputStream(new OutputStream(){
			@Override
			public void write(int b){
			}
			@Override
			public void write(byte[] b, int off, int len){
				output.append(new String(b, off, len));
			}
		});
	}
	
	public void publish(LogRecord record){
		if(!frame.isVisible()) return;
		super.publish(record);
		flush();
	}
}