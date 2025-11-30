package com.theshubhamco.learn.multithreading.creatingThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("Thread name: "+Thread.currentThread().getName());
		return 3*30; //returning result
	}
	
}

public class CallableExample {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor(); 
		Future<Integer> future = executor.submit(new MyCallable());
		
		try {
			System.out.println("Result from the thread: "+ future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
	}

}

