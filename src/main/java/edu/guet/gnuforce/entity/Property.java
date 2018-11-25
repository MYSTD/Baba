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
		 *  "type": "(property|action|you)"
		 *  "value": "(String:CollideResult|String:MoveDirection|"")"
		 * }
		 */
		// TODO
		try {
			InputStream inputStream = new FileInputStream(file);
			return Property.fromFile(inputStream);
		} catch (FileNotFoundException ex) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
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
			case YOU:
				break;
		}
	}

	public static Property fromFile(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		JSONObject obj = new JSONObject(sb.toString());
		Property property;
		try {
			property = new Property(obj);
		} catch (NullPointerException | EnumConstantNotPresentException ex) {
			return null;
		}
		return property;
	}

	public static Property deserialize(String json) {
		JSONObject obj = new JSONObject(json);
		Property property;
		try {
			property = new Property(obj);
		} catch (NullPointerException | EnumConstantNotPresentException ex) {
			return null;
		}
		return property;
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

	public Object getValue() {
		switch (this.type) {
			case PROPERTY:
				return result;
			case ACTION:
				return direction;
			case YOU:
				return null;
		}
		return null;
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
