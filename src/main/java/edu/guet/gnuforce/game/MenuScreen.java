package edu.guet.gnuforce.game;

import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;

public class MenuScreen extends GameScreen {
	public MenuScreen() {
		super("Menu");
		renderer = new RenderEngine();
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		renderer.renderText(g, "Hello, World!", 0, 0);
	}

	private RenderEngine renderer;
}
