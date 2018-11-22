package edu.guet.gnuforce.internal;

import org.jetbrains.annotations.NotNull;

public class Logger {
	public static void log(String msg) {
		Logger.log(LogLevel.INFO, msg);
	}

	public static void log(@NotNull LogLevel level, @NotNull String msg) {
		switch (level) {
			case INFO:
				System.out.println(level.getPrompt() + msg);
				break;
			case WARNING:
			case ERROR:
				System.err.println(level.getPrompt() + msg);
		}
	}
}
