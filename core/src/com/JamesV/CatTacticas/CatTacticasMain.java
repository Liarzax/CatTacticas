package com.JamesV.CatTacticas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fancypath.finding.Map;

public class CatTacticasMain extends ApplicationAdapter {
	SpriteBatch batch;
	World world = new World();
	
	private static final int size = 32;
	
	
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		//world.init();
		
		// fancypath.finding Testing.
		
		Map map = new Map();
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		
		//world.render(batch);
		
		//batch.end();
	}
}
