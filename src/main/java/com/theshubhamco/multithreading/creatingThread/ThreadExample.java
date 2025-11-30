package com.theshubhamco.learn.multithreading.creatingThread;

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println("Thread running: " + Thread.currentThread().getName());
	}
	
}

public class ThreadExample {

	public static void main(String[] args) {
		System.out.println("Main class having thread name: "+ Thread.currentThread().getName());
		
		MyThread thread = new MyThread();//creating thread
		
		/*
		 * What if we we directly execute the run() method, it will just be treated as normal method
		 * and executed by main thread
		*/
		thread.run();
		
		//Starting the thread
		thread.start();
	}

}