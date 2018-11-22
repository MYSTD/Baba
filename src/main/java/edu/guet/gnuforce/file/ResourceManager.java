package edu.guet.gnuforce.file;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.sound.Sound;
import edu.guet.gnuforce.entity.PropertyManager;
import edu.guet.gnuforce.internal.LogLevel;
import edu.guet.gnuforce.internal.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

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
		Logger.log("Now loading properties...");
		PropertyManager pm = PropertyManager.getInstance();
		try {
			URI uri = Objects.requireNonNull(ResourceManager.class.getClassLoader().getResource("/property")).toURI();
			Path path;
			if (uri.getScheme().equals("jar")) {
				FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
				path = fs.getPath("/property");
			} else {
				path = Paths.get(uri);
			}
			Stream<Path> walk = Files.walk(path, 1);
			for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
				Path p = it.next();
				pm.addPropertyFromFile(p.toFile());
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		Logger.log("Loading fonts...");
		InputStream fontInputStream = ResourceManager.class.getResourceAsStream("/font/joystix.ttf");
		try {
			fonts.put("Joystix", Font.createFont(Font.TRUETYPE_FONT, fontInputStream));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
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
