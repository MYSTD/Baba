package edu.guet.gnuforce.game;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.IScreenManager;
import de.gurkenlabs.litiengine.gui.screens.ScreenManager;
import edu.guet.gnuforce.file.ResourceManager;

public class GameInstance {
	public GameInstance() {
		ResourceManager manager = ResourceManager.getInstance();
		manager.loadResources();
		Game.init();
		Game.start();
		Game.addGameTerminatedListener(() -> System.out.println("Thanks for playing!"));

		IScreenManager screenManager = Game.getScreenManager();
		screenManager.addScreen(new MenuScreen());
		screenManager.displayScreen("Menu");
	}
}
