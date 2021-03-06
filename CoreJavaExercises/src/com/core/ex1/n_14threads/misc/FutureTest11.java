package com.core.ex1.n_14threads.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest11 {
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. /usr/local/jdk5.0/src):::");
		String directory = in.nextLine();
		System.out.print("Enter keyword (e.g. test):");
		String keyword = in.nextLine();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		Thread t = new Thread(task);
		t.start();
		try{
			System.out.println(task.get() + " matching files.");
		} catch (ExecutionException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}
}

class MatchCounter implements Callable<Integer>{
	private File directory;
	private String keyword;
	private int count;
	
	/*
	 * Construct a Callable counter
	 * **/
	public MatchCounter(File directory, String keyword){
		this.directory = directory;
		this.keyword = keyword;
	}

	@Override
	public Integer call() throws Exception {
		count = 0;
		try{
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			
			for(File file : files){
				if(file.isDirectory()){
					System.out.println("Directory:" + file.getPath());					
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				} else {
					if (search(file)){
						count ++;
					}
				}
			}
			for (Future<Integer> result : results){
				try{
					count += result.get();
				} catch (ExecutionException e){
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e){
		}
		return count;
	}
	
	/*
	 * Search a file for a given keyboard
	 ***/
	private boolean search(File file) {
		try {
			try (Scanner in = new Scanner(file)){
				boolean found = false;
				while (!found && in.hasNextLine())
				{
					String line = in.nextLine();
					if (line.contains(keyword)){
						found = true;
					}
				}
				return found;
			}
		} catch (IOException e){
		return false;
		}
	}
}