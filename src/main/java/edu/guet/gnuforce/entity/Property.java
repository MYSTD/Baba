package edu.guet.gnuforce.entity;

import java.io.File;

public class Property {
	public static Property fromFile(File file) {
		/**
		 * The file is expected as :
		 * {
		 *  "name": "String",
		 *  "icon": "String:IconName",
		 *  "type": "(property|action)"
		 *  "value": "(String:CollideResult|(up|left|down|right|horizontal|vertical|random))"
		 * }
		 */
		// TODO
		return null;
	}

	public CollideResult resolve() {
		return result;
	}

	public String getName() {
		return name;
	}

	private String name;
	private String icon;
	private CollideResult result;
}
