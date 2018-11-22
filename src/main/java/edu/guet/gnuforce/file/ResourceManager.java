package edu.guet.gnuforce.file;

import com.sun.javafx.tk.FontLoader;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Resources;
import de.gurkenlabs.litiengine.configuration.GameConfiguration;
import de.gurkenlabs.litiengine.configuration.GraphicConfiguration;
import de.gurkenlabs.litiengine.sound.Sound;
import edu.guet.gnuforce.entity.PropertyManager;
import edu.guet.gnuforce.internal.LogLevel;
import edu.guet.gnuforce.internal.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

		//PropertyManager.getInstance().loadPropertiesFromDirectory(new File(base, "properties"));
	}

	public Sound getSound(String name) {
		return sounds.getOrDefault(name, null);
	}

	public Font getFont(String name) {
		return fonts.getOrDefault(name, null);
	}

	private static ResourceManager instance;
	private HashMap<String, Sound> sounds;
	private HashMap<String, Font> fonts;
	private File base;
}
