package edu.guet.gnuforce;

import edu.guet.gnuforce.file.ResourceManager;

public class Main {
	public static void main(String[] args) {
		ResourceManager res = ResourceManager.getInstance();
		res.loadResources();

		System.out.println("Hello, World!");
	}
}

