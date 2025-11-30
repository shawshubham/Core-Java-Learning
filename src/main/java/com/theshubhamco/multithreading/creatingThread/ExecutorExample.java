package com.theshubhamco.learn.multithreading.creatingThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3); //Thread pool of size 3
		
		for(int i=0; i<5; i++) {
			executor.execute(() -> System.out.println("Executing Task: "+Thread.currentThread().getName()));
		}
		
		executor.shutdown();
	}

}
