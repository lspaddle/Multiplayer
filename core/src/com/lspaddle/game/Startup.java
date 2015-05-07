package com.lspaddle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lucas on 4/26/2015.
 * This scene just plays the libgdx logo, "our logo", and hands the scene relay to the main menu
 */
public class Startup implements Scene
{
    Texture tGDXTEXT;
    Sprite gdxtext;
    OrthographicCamera camera;
    Viewport viewport;
    private int time = 0;
    private boolean first = true;
    private Timer counter;
    
    private final int LIBGDX_LOGO_UPTIME;

    public Startup()
    {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        tGDXTEXT = new Texture(Gdx.files.internal("libGDX-RedGlossyNoReflection.png"));
        gdxtext = new Sprite(tGDXTEXT);
        gdxtext.setSize(tGDXTEXT.getWidth(), tGDXTEXT.getHeight());
        gdxtext.setPosition(w/2f-tGDXTEXT.getWidth()/2f, h/2f-tGDXTEXT.getHeight()/2f);

        camera = new OrthographicCamera(w,h);
        camera.update();

        viewport = new FitViewport(w,h,camera);

        LIBGDX_LOGO_UPTIME = 60; //divide in half to get seconds up
        counter = new Timer("counter");
    }

    @Override
    public void draw(Batch batch) {
        if (first)
        {
            counter.scheduleAtFixedRate(new counterTask(),0,50);
            first = false;
        }

        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //finds current fade alpha value (1.0 = full showing, 0.0 = not showing)
        float alpha = (float)time/ LIBGDX_LOGO_UPTIME;
        if (alpha>1){alpha = 1;}
        gdxtext.setAlpha(alpha);
        gdxtext.draw(batch);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,false);
    }

    @Override
    public void dispose() {
        //TO DO: check to try and purge sprite gdxtext.;
        counter.cancel();
        counter.purge();
        tGDXTEXT.dispose();
    }

    private class counterTask extends TimerTask
    {
        @Override
        public void run()
        {
            time++;
        }
    }
}
