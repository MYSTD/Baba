package edu.guet.gnuforce.internal;

public enum LogLevel {
	INFO("[i] "),
	WARNING("[!] "),
	ERROR("[X] ");

	LogLevel(String prop) {
		this.prop = prop;
	}

	public String getPrompt() {
		return prop;
	}

	private String prop;
}
