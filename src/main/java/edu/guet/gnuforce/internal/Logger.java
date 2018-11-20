package edu.guet.gnuforce.internal;

public class Logger {
	public static void log(String msg) {
		Logger.log(LogLevel.INFO, msg);
	}

	public static void log(LogLevel level, String msg) {
		System.out.println(level.getPrompt() + msg);
	}
}
