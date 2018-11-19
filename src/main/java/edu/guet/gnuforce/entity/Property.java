package edu.guet.gnuforce.entity;

import org.json.JSONObject;

import java.io.*;

public class Property {
	/**
	 * 从文件中解析一个属性。文件具体格式见方法内注释。
	 *
	 * @param file 文件
	 * @return 返回解析的属性；如果解析失败则返回 null
	 */
	public static Property fromFile(File file) {
		/*
		 * The file is expected as :
		 * {
		 *  "name": "String",
		 *  "icon": "String:IconName",
		 *  "type": "(property|action)"
		 *  "value": "(String:CollideResult|String:MoveDirection)"
		 * }
		 */
		// TODO
		try {
			InputStream inputStream = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(inputStream);
			JSONObject obj = new JSONObject(reader);
			Property property;
			try {
				property = new Property(obj);
			} catch (NullPointerException | EnumConstantNotPresentException ex) {
				return null;
			}
			return property;
		} catch (FileNotFoundException ex) {
			return null;
		}
	}

	private Property(JSONObject object) {
		this.name = object.getString("name");
		this.icon = object.getString("icon");
		this.type = PropertyType.valueOf(object.getString("type").toUpperCase());
		switch (this.type) {
			case PROPERTY:
				this.result = CollideResult.valueOf(object.getString("value").toUpperCase());
				break;
			case ACTION:
				this.direction = MoveDirection.valueOf(object.getString("value").toUpperCase());
				break;
		}
	}

	public CollideResult resolve() {
		return result;
	}

	public MoveDirection move() {
		return direction;
	}

	public PropertyType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	private String name;
	private String icon;
	private PropertyType type;
	private CollideResult result;
	private MoveDirection direction;
}
