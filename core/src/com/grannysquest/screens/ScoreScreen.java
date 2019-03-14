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
import com.grannysquest.objet.HighScore;
import com.grannysquest.objet.PoliceGameplay;
import com.grannysquest.objet.Score;
import com.grannysquest.objet.User;

/**
 * Classe permettant l'affichage et la gestion de la page d'affichage des scores
 *
 * @author Najim, Roman et Thierry
 */

public class ScoreScreen implements Screen {
    SpriteBatch batch;
    Texture background;
    App game;
    private TextButton tButtonRetour;
    private TextButton tButtonSwitch;
    TextButtonStyle textButtonStyleDisplay;
    TextButton userText;
    private TextButtonStyle boutonSwitch;
    private TextButtonStyle boutonRetour;
    private Skin skin;
    private BitmapFont font;
    private BitmapFont fontGameplay;
    private PoliceGameplay police;
    private Stage stage;
    private int x = Gdx.graphics.getWidth()  / 2;
    int h = Gdx.graphics.getHeight();
    int w = Gdx.graphics.getWidth();
    TextureAtlas buttonAtlas;
    private static int compteur = 0;
    private boolean start = true;

    public ScoreScreen(final App game) {

        this.game = game;
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("media/fond_score.png"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();

        police = new PoliceGameplay();
        fontGameplay = police.getFontGameplay_blanche();;

        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);

        textButtonStyleDisplay= new TextButtonStyle();
        textButtonStyleDisplay.font= fontGameplay;

        configBoutonRetour();
        configBoutonSwitch();
        configUserText();

        tButtonRetour.setBounds(Gdx.graphics.getWidth()  / 2 - tButtonRetour.getWidth() , 20, tButtonRetour.getWidth()*2,tButtonRetour.getHeight()*2);
        tButtonSwitch.setBounds(Gdx.graphics.getWidth()  / 2 - tButtonSwitch.getWidth() , 150, tButtonSwitch.getWidth()*2,tButtonSwitch.getHeight()*2);

        stage.addActor(tButtonRetour);
        stage.addActor(tButtonSwitch);
        stage.addActor(userText);
    }

    public void configUserText(){
        userText = new TextButton("User : "+ User.actif, textButtonStyleDisplay);
        userText.setBounds((int)( w *0.475), (int)( h *0.95), 50, 50);
    }



    @Override
    public void show() {

    }

    /**
     * Méthode permettant l'affichage des scores
     *
     * @param delta
     */

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            choixScore ();
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

    private void configBoutonSwitch() {
        boutonSwitch = new TextButtonStyle();
        boutonSwitch.font = font;
        boutonSwitch.up = skin.getDrawable("switch");
        boutonSwitch.down = skin.getDrawable("switch_jaune");
        tButtonSwitch = new TextButton("", boutonSwitch);
        tButtonSwitch.addListener(new ChangeListener() {
                                      @Override
                                      public void changed(ChangeEvent event, Actor actor) {
                                          compteur++;
                                          new ScoreScreen(game);
                                      }
                                  }
        );
    }

    private void configBoutonRetour() {
        boutonRetour = new TextButtonStyle();
        boutonRetour.font = font;
        boutonRetour.up = skin.getDrawable("retour");
        tButtonRetour = new TextButton("", boutonRetour);
        tButtonRetour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

    }

    private void choixScore (){

        int longueur;
        String [] tab;
        if (compteur % 2 == 0){
            // Récupération des scores
            fontGameplay.draw(batch,"ALL",x - 30,(int) (Gdx.graphics.getHeight() * 0.8)  );
            tab = HighScore.getScore();

        }

        else {
            fontGameplay.draw(batch,User.actif,x - 60 ,(int) (Gdx.graphics.getHeight() * 0.8)  );
            tab = Score.getScore();
        }

        // Condition pour définir le nombre maximum de score à afficher à 10
        if (tab.length < 11 ){
            longueur = tab.length;
        } else {
            longueur = 10;
        }

        // Boucle pour l'affichage des valeurs sur l'écran

        int first = (int) (Gdx.graphics.getHeight() * 0.7);
        for (int i = 0; i < longueur; i++){
            fontGameplay.draw(batch, tab [i], x - tab[i].length()*10, first - i * 50);
        }
    }
}