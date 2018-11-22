package edu.guet.gnuforce.entity;

import edu.guet.gnuforce.internal.LogLevel;
import edu.guet.gnuforce.internal.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.*;
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
	public void loadPropertiesFromDirectory(@NotNull File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.getName().endsWith(".json")) {
					addPropertyFromFile(f);
				}
			}
			Logger.log("Loaded " + String.valueOf(properties.size()) + "properties");
		} else {
			Logger.log(LogLevel.WARNING, "Path `" + file.getAbsolutePath() + "` is not a directory or not readable");
		}
	}

	public void addPropertyFromFile(@NotNull File f) {
		try {
			InputStream inputStream = new FileInputStream(f);
			InputStreamReader reader = new InputStreamReader(inputStream);
			JSONObject obj = new JSONObject(reader);
			String name = obj.getString("name");
			Property property = Property.fromFile(f);
			properties.put(name, property);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static PropertyManager instance = null;
	private HashMap<String, Property> properties;
}
