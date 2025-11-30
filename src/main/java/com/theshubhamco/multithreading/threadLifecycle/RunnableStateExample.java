package com.theshubhamco.learn.multithreading.threadLifecycle;


public class RunnableStateExample {
	public static void main(String[] args) {
		Thread thread = new MyThread();
		thread.start(); 
		
		//Output can be either RUNNABLE or TERMINATED
		System.out.println("Thread State: " + thread.getState()); 
	}

}
