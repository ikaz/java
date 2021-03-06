package com.core.ex2.c_3networking;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author icastillejos
 * @version 1
 */
public class MailTest8 {
	public static void main(String[] args) throws MessagingException, IOException{
		Properties props = new Properties();
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		// 
		
		
		try (InputStream in = Files.newInputStream(Paths.get("src/com/core/ex2/networking3/mail", "mail.properties"))){
			props.load(in);
		} catch (Exception e)
		{
			System.err.println(System.getProperty("user.dir"));
			String ss = e.getMessage();
			ss = ss.toUpperCase();
		}

		List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));
		
		String from = lines.get(0);
		String to = lines.get(1);
		String subject = lines.get(2);
		
		StringBuilder builder = new StringBuilder();
		for (int i=3; i< lines.size(); i++){
			builder.append(lines.get(i));
			builder.append("\n");
		}
		Console console = System.console();
		String password = new String(console.readPassword("Password: "));
		
		Session mailSession = Session.getDefaultInstance(props);
		
		mailSession.setDebug(true);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(builder.toString());
		
		Transport tr = mailSession.getTransport();
		try{
			tr.connect(null, password);
			tr.sendMessage(message, message.getAllRecipients());
		} finally{
			tr.close();
		}			
	}	
}
