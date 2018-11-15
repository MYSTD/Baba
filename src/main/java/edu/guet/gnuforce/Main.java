package edu.guet.gnuforce;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Map map = new Map();
		try {
			map.ReadMap("http://www.baidu.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

