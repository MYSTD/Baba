package edu.guet.gnuforce.entity;

import java.io.File;
import java.util.HashMap;

public class PropertyManager {
	private PropertyManager() {
		properties = new HashMap<>();
	}

	public static PropertyManager getInstance() {
		if (PropertyManager.instance == null) {
			PropertyManager.instance = new PropertyManager();
		}
		return PropertyManager.instance;
	}

	/**
	 * 扫描指定目录下的 JSON 文件并加载为属性。方法不会检查目录的有效性！
	 *
	 * @param file 目录
	 */
	public void loadPropertiesFromDirectory(File file) {
		File[] files = file.listFiles();

	}

	private static PropertyManager instance = null;
	private HashMap<String, Property> properties;
}
