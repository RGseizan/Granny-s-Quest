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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.grannysquest.App;
import com.grannysquest.managers.SoundBank;
import com.grannysquest.objet.User;

import java.util.HashMap;

public class MainMenuScreen implements Screen {

    App game;
    Texture background;

    private Stage stage;

    private TextButton tButtonHelp;
    private TextButton tButtonPlay;
    private TextButton tButtonExit;
    private TextButton tButtonScore;
    private TextButton tButtonOption;
    private TextButton tButtonProfil;
    private TextButtonStyle boutonHelp;
    private TextButtonStyle boutonPlay;
    private TextButtonStyle boutonExit;
    private TextButtonStyle boutonScore;
    private TextButtonStyle boutonOption;
    private TextButtonStyle boutonProfil;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private HashMap<String, TextButtonStyle> list = new HashMap<String, TextButtonStyle>();
    public static MainApp gameEnCours;



    SpriteBatch batch;


    public MainMenuScreen(App game) {

        this.game = game;
        background = new Texture(Gdx.files.internal("media/fond_menu.png"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();

        int h = Gdx.graphics.getHeight();
        int w = Gdx.graphics.getWidth();

        //----------------------------------------------------------------------------
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);

        configBoutonPlay();
        configBoutonHelp();
        configBoutonExit();
        configBoutonScore();
        configBoutonOption();
        configBoutonProfil();



        tButtonPlay.setBounds((int)( w *0.5 - tButtonPlay.getWidth()/ 4) , (int)( h *0.20) , tButtonPlay.getWidth() / 2, tButtonPlay.getHeight() / 2);
        tButtonHelp.setBounds((int)( w *0.5 - tButtonHelp.getWidth()/ 4) , (int)( h *0.14) , tButtonHelp.getWidth() / 2, tButtonHelp.getHeight() / 2);
        tButtonScore.setBounds((int)( w *0.5 - tButtonScore.getWidth()/ 4) , (int)( h *0.08) , tButtonScore.getWidth() / 2, tButtonScore.getHeight() / 2);
        tButtonExit.setBounds((int)( w *0.5 - tButtonExit.getWidth()/ 4) , (int)( h *0.02) , tButtonExit.getWidth() / 2, tButtonExit.getHeight() / 2);


        int ratio = (int) (Gdx.graphics.getWidth() * 0.15);
        tButtonOption.setBounds(w - ratio, h - ratio, ratio, ratio);
        tButtonProfil.setBounds(ratio, Gdx.graphics.getHeight() - ratio, ratio, ratio);
        
        stage.addActor(tButtonPlay);
        stage.addActor(tButtonHelp);
        stage.addActor(tButtonScore);
        stage.addActor(tButtonExit);

        stage.addActor(tButtonOption);
        stage.addActor(tButtonProfil);

        batch = new SpriteBatch();


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        try {
            App.sb.loop("musicMenu");
        } catch (Exception e) {
        }
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
        batch.dispose();
        stage.dispose();
    }

    public void configBoutonPlay() {


        boutonPlay = new TextButtonStyle();
        boutonPlay.font = font;
        boutonPlay.up = skin.getDrawable("blanc_play");
        boutonPlay.down = skin.getDrawable("jaune_play");
        boutonPlay.checked = skin.getDrawable("blanc_play");
        tButtonPlay = new TextButton("", boutonPlay);
        tButtonPlay.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        game.getScreen().dispose();
                                        gameEnCours = new MainApp(game);
                                        App.listScreen.add(gameEnCours);
                                        game.setScreen(gameEnCours);
                                        App.sb.stop("musicMenu");
                                    }
                                }
        );
    }

    private void configBoutonScore() {
        boutonScore = new TextButtonStyle();
        boutonScore.font = font;
        boutonScore.up = skin.getDrawable("blanc_higscore");
        boutonScore.down = skin.getDrawable("jaune_higscore");
        boutonScore.checked = skin.getDrawable("blanc_higscore");
        tButtonScore = new TextButton("", boutonScore);
        tButtonScore.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         game.getScreen().dispose();
                                         game.setScreen(new ScoreScreen(game));
                                     }
                                 }
        );
    }

    private void configBoutonExit() {
        boutonExit = new TextButtonStyle();
        boutonExit.font = font;
        boutonExit.up = skin.getDrawable("blanc_exit");
        boutonExit.down = skin.getDrawable("jaune_exit");
        boutonExit.checked = skin.getDrawable("blanc_exit");
        tButtonExit = new TextButton("", boutonExit);
        tButtonExit.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {

                                        game.toastShort("Merci d'avoir jouer :)");
                                        try {
                                            new Thread().sleep(2000);
                                        } catch (InterruptedException e) {

                                        }
                                        System.exit(0);
                                    }
                                }
        );
    }

    private void configBoutonHelp() {
        boutonHelp = new TextButtonStyle();
        boutonHelp.font = font;
        boutonHelp.up = skin.getDrawable("blanc_help");
        boutonHelp.down = skin.getDrawable("jaune_help");
        boutonHelp.checked = skin.getDrawable("blanc_help");
        tButtonHelp = new TextButton("", boutonHelp);
        tButtonHelp.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        game.getScreen().dispose();
                                        game.setScreen(new HelpScreen(game));
                                    }
                                }
        );

    }

    private void configBoutonOption() {
        boutonOption = new TextButtonStyle();
        boutonOption.font = font;
        boutonOption.up = skin.getDrawable("parametre");
        tButtonOption = new TextButton("", boutonOption);
        tButtonOption.addListener(new ChangeListener() {
                                      @Override
                                      public void changed(ChangeEvent event, Actor actor) {
                                          game.getScreen().dispose();
                                          game.setScreen(new OptionScreen(game));
                                      }
                                  }
        );
    }

    private void configBoutonProfil() {
        boutonProfil = new TextButtonStyle();
        boutonProfil.font = font;
        boutonProfil.up = skin.getDrawable("profil");
        boutonProfil.down = skin.getDrawable("profil_jaune");
        tButtonProfil = new TextButton("", boutonProfil);
        tButtonProfil.addListener(new ChangeListener() {
                                      @Override
                                      public void changed(ChangeEvent event, Actor actor) {
                                          game.getScreen().dispose();
                                          game.setScreen(new ProfileScreen(game));
                                      }
                                  }
        );
    }


}