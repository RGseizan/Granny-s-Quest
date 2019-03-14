package com.grannysquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.grannysquest.App;
import com.grannysquest.managers.database.Difficulte;
import com.grannysquest.objet.PoliceGameplay;

public class LowScoreScreen implements Screen {

    SpriteBatch batch;
    Texture background;
    App game;
    private TextButton tButtonRestart;
    private TextButtonStyle boutonRestart;
    private Skin skin;
    private BitmapFont font;
    private Stage stage;
    TextureAtlas buttonAtlas;
    TextButton.TextButtonStyle textButtonStyleDisplay;
    TextButton textButtonScore;
    int h = Gdx.graphics.getHeight();
    int w = Gdx.graphics.getWidth();

    public LowScoreScreen(final App game) {
        this.game = game;

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("media/fond_partie_finie.png"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);
        boutonRestart = new TextButtonStyle();
        boutonRestart.font = font;
        boutonRestart.up = skin.getDrawable("restart");
        tButtonRestart = new TextButton("", boutonRestart);
        tButtonRestart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        tButtonRestart.setBounds(Gdx.graphics.getWidth() / 2 - tButtonRestart.getWidth(), 20, tButtonRestart.getWidth() * 2, tButtonRestart.getHeight() * 2);
        textButtonStyleDisplay= new TextButton.TextButtonStyle();
        PoliceGameplay police12 =new PoliceGameplay();
        police12.editSize(60, "black");
        BitmapFont fontTimer = police12.getFontGameplay_noire();
        textButtonStyleDisplay.font=fontTimer;
        stage.addActor(tButtonRestart);
        configScore();
        stage.addActor(textButtonScore);

    }

    private void configScore() {
        textButtonScore = new TextButton(""+ MainApp.leScore.setBonus(), textButtonStyleDisplay);
        textButtonScore.setBounds((int)( w *0.4), (int)( h *0.2), 50, 50);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
