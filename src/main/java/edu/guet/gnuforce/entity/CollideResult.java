package edu.guet.gnuforce.entity;

public enum CollideResult {
	CASCADE(0),
	DISPLACE(1),
	CANCEL(2),
	ERASE(3),
	GOAL(4);

	private CollideResult(int x) {
		value = x;
	}

	public int getValue() {
		return value;
	}

	private int value;
}
