package com.grannysquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.grannysquest.constante.ConstanteDataBase;
import com.grannysquest.objet.Param;
import com.grannysquest.objet.PoliceGameplay;
import com.grannysquest.objet.User;

public class ProfileScreen implements Screen {

    App game;
    Texture background;
    Stage stage;
    TextButton textButtonID;
    TextButton quitter;
    TextButton userText;
    TextButtonStyle textButtonStyleR;
    TextButtonStyle textButtonStyleDisplay;
    TextButtonStyle newButon;
    PoliceGameplay plizpolice =new PoliceGameplay();
    BitmapFont font = plizpolice.getFontGameplay_blanche();
    Skin skin;
    TextureAtlas buttonAtlas;

    SpriteBatch batch;
    TextButton tNewButon;
    public static String user = "";
    public static String mdp;
    int h = Gdx.graphics.getHeight();
    int w = Gdx.graphics.getWidth();

    public ProfileScreen(final App game) {

        this.game = game;
        background = new Texture(Gdx.files.internal("media/fond_profil.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);
        textButtonStyleDisplay= new TextButtonStyle();
        textButtonStyleDisplay.font=font;
        configBoutonRetour();
        configBoutonNew();
        configBoutonID();
        configUserText();
        stage.addActor(textButtonID);
        stage.addActor(quitter);
        stage.addActor(tNewButon);
        stage.addActor(userText);

    }
        public void configUserText(){
           userText = new TextButton("User : "+ User.actif, textButtonStyleDisplay);
            userText.setBounds((int)( w *0.475), (int)( h *0.95), 50, 50);
        }
    public  void configBoutonNew(){
        newButon= new TextButtonStyle();
        newButon.up = skin.getDrawable("valider");
        newButon.down = skin.getDrawable("valider_jaune");
        newButon.font=font;
        tNewButon = new TextButton("", newButon);
        tNewButon.addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {

                                    game.getScreen().dispose();
                                    game.setScreen(new MainMenuScreen(game));
                                    if (user.equals("")){
                                        game.toastLong("Please enter your username.");

                                    } else if (user.indexOf(" ") != -1) {
                                        game.toastLong( "Please do not enter any space.");


                                    } else if (!App.udb.onExist("SELECT " + ConstanteDataBase.COLUMN_NAME + " FROM " + ConstanteDataBase.USER_TABLE_NAME +
                                            " WHERE " + ConstanteDataBase.COLUMN_NAME + " = '" + user + "'")) {
                                        new User(user);
                                        game.toastLong("User " + user + " connected.");


                                    } else {
                                        new User(user);
                                        game.toastLong("User " + user + " created.");
                                    }

                                }
                            }
        );
        tNewButon.setBounds((int)( w *0.2), (int)( h *0.2), tNewButon.getWidth()*3/2, tNewButon.getHeight()*3/2);
    }

    private void configBoutonID() {
        textButtonID = new TextButton("Saisie", textButtonStyleDisplay);
        textButtonID.setBounds((int)( w *0.45), (int)( h *0.55), 50, 50);
        textButtonID.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         Gdx.input.getTextInput(new Input.TextInputListener() {
                                             @Override
                                             public void input(String text) {
                                                 user = text;
                                             }

                                             @Override
                                             public void canceled() {
                                             }
                                         },"Please enter your username", "", "Identifiant");
                                     }
                                 });
    }


    public  void configBoutonRetour(){
        textButtonStyleR = new TextButtonStyle();
        textButtonStyleR.up = skin.getDrawable("retour");
        textButtonStyleR.down = skin.getDrawable("retour_jaune");
        textButtonStyleR.font=font;
        quitter = new TextButton("", textButtonStyleR);
        quitter.addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {
                                    game.toastLong("Changement annulé!");
                                    game.getScreen().dispose();
                                    game.setScreen(new MainMenuScreen(game));

                                }
                            }
        );
        quitter.setBounds((int)( w *0.6), (int)( h *0.2), quitter.getWidth()*3/2, quitter.getHeight()*3/2);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "Login :", (int)( w *0.4), (int)( h *0.65));
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