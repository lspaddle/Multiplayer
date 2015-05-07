package com.lspaddle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Scene current;


	@Override
	public void create () {
		batch = new SpriteBatch();
		current = new Startup();
	}

	@Override
	public void dispose() {
		batch.dispose();
		current.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        current.draw(batch);
		batch.end();
	}

	@Override
	public void resize (int width, int height) {
		current.resize(width,height);
	}

    public void setCurrentScene(Scene scene){
        current = scene;
    }
}
