package edu.guet.gnuforce.file;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.sound.Sound;
import edu.guet.gnuforce.entity.PropertyManager;
import edu.guet.gnuforce.internal.LogLevel;
import edu.guet.gnuforce.internal.Logger;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ResourceManager {

	private ResourceManager() {
		base = new File("resources");
		if (!base.isDirectory()) {
			Logger.log(LogLevel.ERROR, String.format("Cannot find resources/ under this location: %s", base.getAbsolutePath()));
			throw new ResourceNotFoundError();
		}
		fonts = new HashMap<>();
		sounds = new HashMap<>();
		Logger.log("Resource manager created");
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
		AtomicInteger counter = new AtomicInteger();
		walkFolderAndLoadStuffToMap("property/", "json", ((ignored, inputStream) -> {
			try {
				pm.addPropertyFromFile(inputStream);
				counter.incrementAndGet();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));
		Logger.log(String.format("%d properties loaded", counter.get()));
		counter.set(0);
		Logger.log("Loading fonts...");
		walkFolderAndLoadStuffToMap("font/", "ttf", ((path, inputStream) -> {
			String[] chunks = path.split("/");
			String fontName = chunks[chunks.length - 1];
			try {
				fonts.put(fontName, Font.createFont(Font.TRUETYPE_FONT, inputStream));
				counter.incrementAndGet();
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}
		}));
		Logger.log(String.format("%d fonts loaded", counter.get()));
		counter.set(0);
		// TODO: load sound
		Logger.log("Resources loaded");
	}

	private interface IResourceLoader {
		void loadResource(String path, InputStream inputStream);
	}

	private void walkFolderAndLoadStuffToMap(String folder, String extension, IResourceLoader loader) {
		try {
			URI uri = Objects.requireNonNull(ResourceManager.class.getClassLoader().getResource(folder)).toURI();
			FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
			Path path;
			if (uri.getScheme().equals("jar")) {
				path = fs.getPath(folder);
			} else {
				path = Paths.get(uri);
			}
			Files.walk(path, 1).filter(p -> p.toString().endsWith(extension)).forEach(it -> {
				try {
					loader.loadResource(it.toString(), Files.newInputStream(it));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			fs.close();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Sound getSound(String name) {
		return sounds.getOrDefault(name, null);
	}

	public Font getFont(String name) {
		return fonts.getOrDefault(name, null);
	}

	public File getExternalGameMapFolder() {
		File folder = new File(base, "extmap");
		if (!folder.exists()) {
			if (!folder.mkdirs()) {
				throw new ResourceNotFoundError();
			}
		}
		return folder;
	}

	private static ResourceManager instance;
	private HashMap<String, Sound> sounds;
	private HashMap<String, Font> fonts;
	private File base;
}
