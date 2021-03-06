package com.core.ex2.c_3networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/* This makes a socket connection and prints the time
 * 
 * @version 1
 * @author me
 */
public class SocketTest1 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13))
		{
			InputStream inStream = s.getInputStream();
			Scanner in = new Scanner(inStream);
			
			while (in.hasNextLine()){
				String line = in.nextLine();
				System.out.println(line);
			}			
		}
	}
}
