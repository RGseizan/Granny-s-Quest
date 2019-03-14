package com.grannysquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.grannysquest.objet.Param;
import com.grannysquest.objet.PoliceGameplay;
/**
 * Classe permettant l'affichage et la gestion de la page des options
 * Listes des options :
 * -Le volume de la music
 * -Le volume des bruitages
 * -La difficulte du jeux
 *
 * @author  Roman
 */
public class OptionScreen implements Screen {

   
    App game;
    Texture background;
    Stage stage;
    TextButton valider;
    TextButton quitter;
    TextButtonStyle textButtonStyleV;
    TextButtonStyle textButtonStyleR;
    TextButtonStyle textButtonStyleDisplay;
    TextButton textButtonM;
    TextButton textButtonB;
    TextButton textButtonD;
    PoliceGameplay bob =new PoliceGameplay();
    BitmapFont font = bob.getFontGameplay_blanche();
    Skin skin;
    TextureAtlas buttonAtlas;
    TextButtonStyle boutonRight;
    TextButtonStyle boutonLeft;
    SpriteBatch batch;
    TextButton tButtonLeftM;
    TextButton tButtonRightM;
    TextButton tButtonLeftB;
    TextButton tButtonRightB;
    TextButton tButtonLeftD;
    TextButton tButtonRightD;
    int h = Gdx.graphics.getHeight();
    int w = Gdx.graphics.getWidth();
    public static int valueM=5;
    public static int valueB=5;
    public static int valueD=5;
    int tempos=0;
    int valueMTra=valueM;
    int valueBTra=valueB;
    int valueDTra=valueD;
    /**
     * Le constructeur de l'optionscreen
     * instensie le background, les bouttons et les textes
     *
     * @param game le jeux en cours
     */
    public OptionScreen( App game) {

        this.game = game;
        background = new Texture(Gdx.files.internal("media/fond_option.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);
        textButtonStyleDisplay= new TextButtonStyle();
        textButtonStyleDisplay.font=font;
        boutonRight = new TextButtonStyle();
        boutonRight.font = font;
        boutonRight.up = skin.getDrawable("buton_right");
        boutonRight.down = skin.getDrawable("buton_right_pressed");
        boutonLeft = new TextButtonStyle();
        boutonLeft.font = font;
        boutonLeft.up = skin.getDrawable("buton_left");
        boutonLeft.down = skin.getDrawable("buton_left_pressed");
        font.setColor(new Color(Color.WHITE));
        configBoutonLeftM();
        configBoutonRightM();
        configBoutonLeftB();
        configBoutonRightB();
        configBoutonLeftD();
        configBoutonRightD();
        configBoutonValider();
        configBoutonRetour();
        configBoutonVDM();
        configBoutonVDB();
        configBoutonVDD();
        stage.addActor(tButtonLeftM);
        stage.addActor(textButtonM);
        stage.addActor(tButtonRightM);
        stage.addActor(tButtonLeftB);
        stage.addActor(textButtonB);
        stage.addActor(tButtonRightB);
        stage.addActor(tButtonLeftD);
        stage.addActor(textButtonD);
        stage.addActor(tButtonRightD);
        stage.addActor(valider);
        stage.addActor(quitter);
    }
    /**
     *Les trois methodes suivantes configure le text présent entre les boutons + et - des option
     *dans l'ordre :
     * music
     * bruitage
     * difficulté
     */
    private void configBoutonVDM() {
         textButtonM = new TextButton(valueM+"", textButtonStyleDisplay);
        textButtonM.setBounds((int)( w *0.5), (int)( h *0.7), 50, 50);
    }
    private void configBoutonVDB() {
         textButtonB= new TextButton(valueB+"", textButtonStyleDisplay);
        textButtonB.setBounds((int)( w *0.5), (int)( h *0.5), 50, 50);
    }
    private void configBoutonVDD() {
        textButtonD = new TextButton(valueD+"", textButtonStyleDisplay);
        textButtonD.setBounds((int)( w *0.5), (int)( h *0.3), 50, 50);
    }
    /**
     *Cette method applique les changements
     */
    public  void configBoutonValider(){
        textButtonStyleV = new TextButtonStyle();
        textButtonStyleV.up = skin.getDrawable("valider_jaune");
        textButtonStyleV.down = skin.getDrawable("valider");
        textButtonStyleV.font=font;
        valider = new TextButton("", textButtonStyleV);
        valider.addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {
                                     App.sb.volume("musicpop",valueM);
                                     App.sb.volume("bru",valueB);
                                    Param.addTable();
                                    game.toastLong("Changement sauvegardé!");
                                    game.getScreen().dispose();
                                    game.setScreen(new MainMenuScreen(game));

                                }
                            }
        );
        valider.setBounds((int)( w *0.2), (int)( h *0.2), valider.getWidth(), valider.getHeight());
    }
    public  void configBoutonRetour(){
        textButtonStyleR = new TextButtonStyle();
        textButtonStyleR.up = skin.getDrawable("retour_jaune");
        textButtonStyleR.down = skin.getDrawable("retour");
        textButtonStyleR.font=font;
        quitter = new TextButton("", textButtonStyleR);
        quitter.addListener(new ChangeListener() {
                                @Override
                                public void changed(ChangeEvent event, Actor actor) {
                                    valueM=valueMTra;
                                    valueB=valueBTra;
                                    valueD=valueDTra;
                                    game.toastLong("Changement annulé!");
                                    game.getScreen().dispose();
                                    game.setScreen(new MainMenuScreen(game));

                                }
                            }
        );
        quitter.setBounds((int)( w *0.6), (int)( h *0.2), quitter.getWidth(), quitter.getHeight());

    }
    public void configBoutonLeftM() {
        tButtonLeftM = new TextButton("", boutonLeft);
        tButtonLeftM.setBounds((int)( w *0.75), (int)( h *0.68), 50, 50);
        tButtonLeftM.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(valueM<10){
                                            valueM++;
                                            textButtonM.setText(valueM+"");
                                        }
                                    }
                                }
        );
    }

    public void configBoutonRightM() {

        tButtonRightM = new TextButton("", boutonRight);
        tButtonRightM.setBounds((int)( w *0.25), (int)( h *0.68), 50, 50);
        tButtonRightM.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(valueM>1){
                                            valueM--;
                                            textButtonM.setText(valueM+"");
                                        }
                                    }
                                }
        );
    }

    public void configBoutonLeftB() {

        tButtonLeftB = new TextButton("", boutonLeft);
        tButtonLeftB.setBounds((int)( w *0.75), (int)( h *0.48), 50, 50);
        tButtonLeftB.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(valueB<10){
                                            valueB++;
                                            textButtonB.setText(valueB+"");
                                        }
                                    }
                                }
        );
    }

    public void configBoutonRightB() {
        tButtonRightB = new TextButton("", boutonRight);
        tButtonRightB.setBounds((int)( w *0.25), (int)( h *0.48), 50, 50);
        tButtonRightB.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         if(valueB>1){
                                             valueB--;
                                             textButtonB.setText(valueB+"");
                                         }
                                     }
                                 }
        );
    }
    public void configBoutonLeftD() {
        tButtonLeftD = new TextButton("", boutonLeft);
        tButtonLeftD.setBounds((int)( w *0.75), (int)( h *0.28), 50, 50);
        tButtonLeftD.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(valueD<10){
                                            valueD++;
                                            textButtonD.setText(valueD+"");
                                        }
                                    }
                                }
        );
    }

    public void configBoutonRightD() {
        tButtonRightD = new TextButton("", boutonRight);
        tButtonRightD.setBounds((int)( w *0.25), (int)( h *0.28), 50, 50);
        tButtonRightD.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         if(valueD>1){
                                             valueD--;
                                             textButtonD.setText(valueD+"");
                                         }
                                     }
                                 }
        );
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
       System.err.print( font.getColor() ) ;
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "Volume de la musique :", (int)( w *0.15), (int)( h *0.8));
        font.draw(batch, "Volume des bruitages :", (int)( w *0.15), (int)( h *0.6));
        font.draw(batch, "Niveau de difficulte :", (int)( w *0.15), (int)( h *0.4));
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
