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
import com.grannysquest.objet.PoliceGameplay;

public class HelpScreen implements Screen {

    App game;

    SpriteBatch batch1;
    SpriteBatch batch2;
    Texture background;
    private TextButton tButtonRetour;
    private TextButtonStyle boutonRetour;
    private Skin skin;
    PoliceGameplay helpScreenPoliceGrande;
    PoliceGameplay helpScreenPolicePetite;
    BitmapFont fontJaune;
    BitmapFont fontBlanche;

    private Stage stage;
    TextureAtlas buttonAtlas;
    int h = Gdx.graphics.getHeight();
    int w = Gdx.graphics.getWidth();
    public HelpScreen(final App game) {
        this.game = game;
        helpScreenPoliceGrande =new PoliceGameplay();
        helpScreenPolicePetite =new PoliceGameplay();
        helpScreenPolicePetite.editSize(16,"white");
        fontBlanche = helpScreenPolicePetite.getFontGameplay_blanche();
        fontJaune = helpScreenPoliceGrande.getFontGameplay_jaune();
        System.err.println(fontBlanche.getColor());
        batch1 = new SpriteBatch();
        batch2 = new SpriteBatch();
        background = new Texture(Gdx.files.internal("media/fond_help.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin.addRegions(buttonAtlas);
        boutonRetour = new TextButtonStyle();
        boutonRetour.font = fontBlanche;
        boutonRetour.up = skin.getDrawable("retour");
        tButtonRetour = new TextButton("", boutonRetour);
        tButtonRetour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.getScreen().dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        tButtonRetour.setBounds(Gdx.graphics.getWidth() / 2 - tButtonRetour.getWidth(), 20, tButtonRetour.getWidth() * 2, tButtonRetour.getHeight() * 2);
        stage.addActor(tButtonRetour);
    }

    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        batch1.begin();
        batch1.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        fontJaune.draw(batch1, "But du jeu :", (int)( w *0.25), (int)( h *0.8));
        fontJaune.draw(batch1, "Commande :", (int)( w *0.25), (int)( h *0.6));
        fontJaune.draw(batch1, "Credit :", (int)( w *0.25), (int)( h *0.4));
        batch1.end();

        batch2.begin();
        fontBlanche = helpScreenPolicePetite.getFontGameplay_blanche();
        fontBlanche.draw(batch2, "Dans un supermarche, Granny fait ses courses,", (int)( w *0.01), (int)( h *0.75));
        fontBlanche.draw(batch2, "elle regarde sa montre et mince !", (int)( w *0.01), (int)( h *0.73));
        fontBlanche.draw(batch2, "C'est bientot les feux de l'amour !", (int)( w *0.01), (int)( h *0.71));
        fontBlanche.draw(batch2, "A vous de l'aider a atteindre la caisse a temps,", (int)( w *0.01), (int)( h *0.69));
        fontBlanche.draw(batch2, "en ramassant un maximum d'articles !", (int)( w *0.01), (int)( h *0.67));
        fontBlanche.draw(batch2, "Utiliser les fleches pour deplacer Granny ", (int)( w *0.01), (int)( h *0.55));
        fontBlanche.draw(batch2, "entre les rayons et lui faire atteindre la caisse.", (int)( w *0.01), (int)( h *0.53));
        fontBlanche.draw(batch2, "Utiliser le boutton d'action devant la caisse ", (int)( w *0.01), (int)( h *0.51));
        fontBlanche.draw(batch2, "pour finaliser les courses et valider votre score.", (int)( w *0.01), (int)( h *0.49));
        fontBlanche.draw(batch2, "-Musique : Challenge Accepted! Free Music By", (int)( w *0.01), (int)( h *0.35));
        fontBlanche.draw(batch2, "-HeatleyBros Link : https://youtu.be/UHClzbbpTds  ", (int)( w *0.01), (int)( h *0.33));
        fontBlanche.draw(batch2, "-SoundBank : http://www.universal-soundbank.com/", (int)( w *0.01), (int)( h *0.31));
        fontBlanche.draw(batch2, "-Oldlady Shutter-stock: old-lady-walker-91915634", (int)( w *0.01), (int)( h *0.29));
        fontBlanche.draw(batch2, "-Application : G.roman, J.Najim", (int)( w *0.01), (int)( h *0.27));
        fontBlanche.draw(batch2, "-Application : G.Theirry, J.Thomas", (int)( w *0.01), (int)( h *0.25));
        batch2.end();
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
        batch1.dispose();
        batch2.dispose();
    }
}
