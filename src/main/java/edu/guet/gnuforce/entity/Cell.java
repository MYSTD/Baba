package edu.guet.gnuforce.entity;

import java.util.ArrayList;

public class Cell {
	public Cell() {
		entities = new ArrayList<>();

	}

	public void transferTo(Cell cell) {

	}

	public ArrayList<Entity> getArrayList() {
		return entities;
	}

	public Entity getTopEntity() {
		if (entities.size() == 1) {
			return entities.get(0);
		} else {
			return entities.get(1);
		}
	}

	private ArrayList<Entity> entities;
}
