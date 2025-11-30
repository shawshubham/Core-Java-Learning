package com.theshubhamco.learn.multithreading.creatingThread;

class ErrorThread implements Runnable{
	@Override
	public void run() {
		throw new RuntimeException("Something went wrong!");
	}
}

public class ExceptionHandlingExample {
	public static void main(String[] args) {
		Thread thread = new Thread(new ErrorThread());
		thread.setUncaughtExceptionHandler((t, e) -> System.out.println("Error in thread: " + t.getName() + " : " + e.getMessage()));
		thread.start();
	}
}
