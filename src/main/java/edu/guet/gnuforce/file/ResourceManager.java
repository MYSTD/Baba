package edu.guet.gnuforce.file;

import edu.guet.gnuforce.entity.PropertyManager;

import java.io.File;

public class ResourceManager {

	private ResourceManager() {
		base = new File("resource");
		if (!base.isDirectory()) {
			base.mkdirs();
		}
	}

	public static ResourceManager getInstance() {
		if (ResourceManager.instance == null) {
			ResourceManager.instance = new ResourceManager();
		}
		return ResourceManager.instance;
	}

	public void loadResources() {
		PropertyManager.getInstance().loadPropertiesFromDirectory(new File(base, "properties"));
	}

	private static ResourceManager instance;
	private File base;
}
