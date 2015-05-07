package com.lspaddle.game;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Lucas on 4/26/2015.
 * The scene interface that the main class uses to run the game
 */
public interface Scene
{
    void draw(Batch batch);
    void dispose();
    void resize(int width, int height);
}
