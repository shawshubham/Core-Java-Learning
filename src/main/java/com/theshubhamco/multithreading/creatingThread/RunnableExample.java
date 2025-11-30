package com.theshubhamco.learn.multithreading.creatingThread;


class MyRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("Runnable class thread name: "+Thread.currentThread().getName());
	}
	
}

public class RunnableExample {

	public static void main(String[] args) {
		Thread thread = new Thread(new MyRunnable());
		thread.start();
		System.out.println("Main thread is ending: "+ Thread.currentThread().getName());
	}

}