package edu.guet.gnuforce.file;

import de.gurkenlabs.litiengine.Game;
import edu.guet.gnuforce.entity.PropertyManager;
import edu.guet.gnuforce.internal.LogLevel;
import edu.guet.gnuforce.internal.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class ResourceManager {

	private ResourceManager() {
		base = new File("resources");
		if (!base.isDirectory()) {
			Logger.log(LogLevel.ERROR, String.format("Cannot find resources/ under this location: %s", base.getAbsolutePath()));
			throw new ResourceNotFoundError();
		}
		Logger.log("Resource manager created!");
	}

	public static ResourceManager getInstance() {
		if (ResourceManager.instance == null) {
			ResourceManager.instance = new ResourceManager();
		}
		return ResourceManager.instance;
	}

	public void loadResources() {
		Game.setInfo("resources/game.xml");
		PropertyManager.getInstance().loadPropertiesFromDirectory(new File(base, "properties"));
	}

	private static ResourceManager instance;
	private File base;
}
