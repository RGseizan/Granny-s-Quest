package com.grannysquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
import com.grannysquest.App;
import com.grannysquest.gameObjects.Map;
import com.grannysquest.gameObjects.MapRenderer;
import com.grannysquest.managers.database.Difficulte;
import com.grannysquest.objet.HighScore;
import com.grannysquest.objet.PoliceGameplay;
import com.grannysquest.objet.Score;

import java.util.Random;

public class MainApp extends ScreenAdapter {

    private final static int TILE_SIZE_X = 32;
    private final static int TILE_SIZE_Y = 32;

    private final static int SPRITE_SIZE_X = 64;
    private final static int SPRITE_SIZE_Y = 64;

    private App game;

    private boolean caisseHit = false;

	private SpriteBatch batch;
	private Sprite granny;
	private Texture granny_north;
    private Texture granny_south;
    private Texture granny_east;
    private Texture granny_west;
    private Sprite checkout;

    private int tileX = 30;
    private int tileY = 30;
	private Map myMap;
    private OrthographicCamera camera;
    private MapRenderer tiledMapRenderer;

    private float w;
    private float h;

    private int oldX = 0;
    private int oldY = 0;

    private int posX;
    private int posY;

    //UI
    private TextButton tButtonUp;
    private TextButton tButtonDown;
    private TextButton tButtonLeft;
    private TextButton tButtonRight;
    private TextButton tButtonAction;
    private TextButton tButtonPause;
    private TextButton.TextButtonStyle boutonUp;
    private TextButton.TextButtonStyle boutonDown;
    private TextButton.TextButtonStyle boutonLeft;
    private TextButton.TextButtonStyle boutonRight;
    private TextButton.TextButtonStyle boutonAction;
    private TextButton.TextButtonStyle boutonPause;
    private TextButton tButtonRetour;
    private TextButton.TextButtonStyle boutonRetour;
    private Skin skin;
    private BitmapFont font;
    private Stage stage;
    private TextButton.TextButtonStyle textButtonStyleDisplay;
    private TextButton textButtonScore;
    private TextButton textButtonTimer;

    TextureAtlas buttonAtlas;

    public static Difficulte leScore;
    int diffiTimer = Difficulte.setTemps();
    public Boolean pause=false;
	public MainApp (App game) {
        this.game = game;
        leScore= new Difficulte();
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        oldX = posX;
        oldY = posY;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        PoliceGameplay policemen =new PoliceGameplay();
        font = policemen.getFontGameplay_noire();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("media/Joystick/joystick.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyleDisplay= new TextButton.TextButtonStyle();


        textButtonStyleDisplay.font=font;
        configBoutonUp();
        configBoutonDown();
        configBoutonRight();
        configBoutonLeft();
        configBoutonAction();
        configBoutonPause();
        configBoutonRetour();
        configTimer();
        configScore();
        tButtonDown.setPosition(110, 100);
        tButtonLeft.setPosition(10, 200);
        tButtonRight.setPosition(210, 200);
        tButtonUp.setPosition(110, 300);
        tButtonAction.setPosition(Gdx.graphics.getWidth() - 200, 200);
        tButtonPause.setBounds(Gdx.graphics.getWidth() / 2 - tButtonPause.getWidth() / 4 - 40, 10 ,tButtonPause.getWidth() / 2, tButtonPause.getHeight() / 2 );
        tButtonRetour.setBounds(Gdx.graphics.getWidth() / 2 - tButtonRetour.getWidth() / 4 + 40, 10 ,tButtonRetour.getWidth() / 2, tButtonRetour.getHeight() / 2 );



        stage.addActor(tButtonUp);
        stage.addActor(tButtonRight);
        stage.addActor(tButtonLeft);
        stage.addActor(tButtonDown);
        //stage.addActor(tButtonAction);
        stage.addActor(tButtonPause);
        stage.addActor(tButtonRetour);
        stage.addActor(textButtonScore);
        stage.addActor(textButtonTimer);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();

        myMap = new Map(tileX,tileY);
        tiledMapRenderer = new MapRenderer(myMap,batch);
        batch = new SpriteBatch();

        granny_north = new Texture(Gdx.files.internal("personnage/av_ar.png"));
        granny_south = new Texture(Gdx.files.internal("personnage/b_ar.png"));
        granny_west = new Texture(Gdx.files.internal("personnage/g_ar.png"));
        granny_east = new Texture(Gdx.files.internal("personnage/d_ar.png"));
        granny = new Sprite(granny_north);

        checkout = new Sprite(new Texture(Gdx.files.internal("media/checkout.png")));
        checkout.setPosition(64,28*64);

	}

	@Override
    public void show() {
        posX=Math.round(tileX*TILE_SIZE_X);
        posY=Math.round(tileY*TILE_SIZE_Y);
        camera.translate(posX - camera.viewportWidth/2,posY - camera.viewportHeight/2);
        oldX = posX;
        oldY = posY;

    }

    @Override
    public void dispose() {
        super.dispose();
            pause=true;
        if(tiledMapRenderer!=null) {
            tiledMapRenderer.dispose();

        }
        if(myMap!=null) {

            myMap.dispose();
        }
        stage.clear();

        App.sb.stop("musicMenu");
        App.sb.stop("musicStage");
        System.err.println("> J'ai disposé");
    }

    @Override
    public void pause() {
        App.sb.pause("musicStage");
        super.pause();
        pause=true;
    }

    @Override
    public void resume() {
	    super.resume();
        App.sb.play("musicStage");
        pause=false;
    }
    @Override
    public void render (float delta) {
        App.sb.stop("musicMenu");
	    //App.sb.stop("musicStage");
        App.sb.loop("musicStage");
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        tiledMapRenderer.setView(camera);
        batch.begin();
        tiledMapRenderer.render();
        batch.end();

        MapObjects obstacles = myMap.getLayers().get(1).getObjects();

       for (RectangleMapObject rectangleObject : obstacles.getByType(RectangleMapObject.class)) {

          Rectangle rectangle = rectangleObject.getRectangle();

           if (Intersector.overlaps(rectangle, granny.getBoundingRectangle())) {
               posX = oldX;
               posY = oldY;
           }
        }

        MapObjects collectibles = myMap.getLayers().get(2).getObjects();

        for (RectangleMapObject rectangleObject : collectibles.getByType(RectangleMapObject.class)) {

            Rectangle rectangle = rectangleObject.getRectangle();

            if (Intersector.overlaps(rectangle, granny.getBoundingRectangle())) {
                App.sb.play("bruitRamassageObjet");
                leScore.listScore.add(1);
                textButtonScore.setText("Score :"+leScore.setBonus());
                myMap.getLayers().get(2).getObjects().remove(rectangleObject);
                ((TiledMapTileLayer)myMap.getLayers().get(2)).setCell(Math.round(rectangle.x/64),Math.round(rectangle.y/64),null);

            }
        }

        if (Intersector.overlaps(checkout.getBoundingRectangle(), granny.getBoundingRectangle())) {
            if (!caisseHit) {
                leScore.listScore.add(1);
            caisseHit = true;
        }
        }


        batch.begin();
        Gdx.app.log("Debug","Position de Granny : " + posX + "," +posY);
        Gdx.app.log("Debug","Position de La Caisse : " + myMap.getCheckoutX()*64 + "," +myMap.getCheckoutY()*64);
        granny.setPosition(posX,posY);
        granny.draw(batch);
        checkout.draw(batch);
        batch.end();
        stage.draw();
    }

    private void configBoutonAction() {
        boutonAction = new TextButton.TextButtonStyle();
        boutonAction.font = font;
        boutonAction.up = skin.getDrawable("action");
        boutonAction.down = skin.getDrawable("action_red");

        tButtonAction = new TextButton("", boutonAction);
        tButtonAction.addListener(new ChangeListener() {
                                      @Override
                                      public void changed(ChangeEvent event, Actor actor) {
                                          if(!pause){
                                              App.sb.play("bruitBouttonAction");
                                          }

                                      }
                                  }
        );
    }
    private void configBoutonPause() {
        boutonPause = new TextButton.TextButtonStyle();
        boutonPause.font = font;
        boutonPause.up = skin.getDrawable("pause");
        boutonPause.checked = skin.getDrawable("pause_red");


        tButtonPause = new TextButton("", boutonPause);
        tButtonPause.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         if(pause){
                                             game.getScreen().resume();
                                             game.toastLong("Game resumed");
                                             App.sb.play("musicStage");
                                         }else{
                                             game.getScreen().pause();
                                             game.toastLong("Game paused");
                                             App.sb.pause("musicStage");
                                             App.sb.allPausing();

                                         }


                                     }
                                 }
        );
    }
    private void configTimer() {
        textButtonTimer = new TextButton("Temps :"+diffiTimer, textButtonStyleDisplay);
        textButtonTimer.setBounds((int)( w *0.65), (int)( h *0.95), 50, 50);
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {//<-s'execute à chaque interval qui passe
                               System.err.println("----->>>>>"+MainApp.leScore.setBonus());
                               if(pause){
                               }else{
                                   diffiTimer--;//<- creer un int qui s'incremente à chaque seconde
                               }
                               textButtonTimer.setText("Temps :"+diffiTimer);//<-met à jour l'afficher qui se met à jour
                               System.err.println("timer >"+diffiTimer);

                               if(caisseHit) {
                                   Timer.instance().clear();

                                   if (leScore.setBonus()>HighScore.getHighScore()){
                                       App.sb.play("musicWin");
                                       game.setScreen(new NewHighScoreScreen(game));

                                   }else{
                                       App.sb.play("musicWin");
                                       game.setScreen(new LowScoreScreen(game));
                                   }

                                   new Score(MainApp.leScore.setBonus());
                                   HighScore.addScore(MainApp.leScore.setBonus());
                                   Difficulte.resetScore();

                               }
                               switch (diffiTimer){
                                   case 5:
                                       App.sb.play("bruitTimmerEnd");
                                       break;
                                   case 0:
                                       App.sb.stop("musicStage");
                                       App.sb.play("musicLoose");
                                       game.getScreen().dispose();
                                       game.setScreen(new GameOverScreen(game));

                                       Timer.instance().clear();
                                       Difficulte.resetScore();
                                       break;
                                   default:
                                       break;
                               }
                           }
                       }
                , 0      //    (delay)<- le timer commence à parir de [valeur] secondes
                , 1    //    (seconds)<- execute run à chaque [valeur] seconde
        );
    }
    private void configScore() {
        textButtonScore = new TextButton("Score :"+leScore.setBonus(), textButtonStyleDisplay);
        textButtonScore.setBounds((int)( w *0.25), (int)( h *0.95), 50, 50);
    }
    private void configBoutonRetour() {
        boutonRetour = new TextButton.TextButtonStyle();
        boutonRetour.font = font;
        boutonRetour.up = skin.getDrawable("back");
        boutonRetour.down = skin.getDrawable("back_red");

        tButtonRetour = new TextButton("", boutonRetour);
        tButtonRetour.addListener(new ChangeListener() {
                                      @Override
                                      public void changed(ChangeEvent event, Actor actor) {
                                          game.getScreen().dispose();
                                          game.setScreen(new MainMenuScreen(game));
                                      }
                                  }
        );
    }
    private void configBoutonUp() {
        boutonUp = new TextButton.TextButtonStyle();
        boutonUp.font = font;
        boutonUp.up = skin.getDrawable("up");
        boutonUp.down = skin.getDrawable("up_red");

        tButtonUp = new TextButton("", boutonUp);
        tButtonUp.addListener(new ChangeListener() {
                                  @Override
                                  public void changed(ChangeEvent event, Actor actor) {
                                      if(!pause){
                                          oldX = posX;
                                          oldY = posY;
                                          posY += TILE_SIZE_Y;
                                          granny.setTexture(granny_north);
                                          if(posY >  camera.position.y + camera.viewportHeight/2 - 2*SPRITE_SIZE_Y) {
                                              camera.translate(0, SPRITE_SIZE_Y);
                                          }
                                      }


                                  }
                              }
        );
    }
    private void configBoutonDown() {
        boutonDown = new TextButton.TextButtonStyle();
        boutonDown.font = font;
        boutonDown.up = skin.getDrawable("down");
        boutonDown.down = skin.getDrawable("down_red");

        tButtonDown = new TextButton("", boutonDown);
        tButtonDown.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(!pause){
                                            oldX = posX;
                                            oldY = posY;
                                            posY -= TILE_SIZE_Y;
                                            granny.setTexture(granny_south);
                                            if(posY < camera.position.y - camera.viewportHeight/2-2*SPRITE_SIZE_Y) {
                                                camera.translate(0,-SPRITE_SIZE_Y);
                                            }
                                        }

                                    }
                                }
        );
    }
    private void configBoutonLeft() {
        boutonLeft = new TextButton.TextButtonStyle();
        boutonLeft.font = font;
        boutonLeft.up = skin.getDrawable("left");
        boutonLeft.down = skin.getDrawable("left_red");

        tButtonLeft = new TextButton("", boutonLeft);
        tButtonLeft.addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ChangeEvent event, Actor actor) {
                                        if(!pause){
                                            oldX = posX;
                                            oldY = posY;
                                            posX -= TILE_SIZE_X;
                                            granny.setTexture(granny_west);
                                            if(posX < camera.position.x -camera.viewportWidth/2 + 2*SPRITE_SIZE_X) {
                                                camera.translate(-SPRITE_SIZE_X,0);
                                            }
                                        }


                                    }
                                }
        );
    }
    private void configBoutonRight() {
        boutonRight = new TextButton.TextButtonStyle();
        boutonRight.font = font;
        boutonRight.up = skin.getDrawable("right");
        boutonRight.down = skin.getDrawable("right_red");

        tButtonRight = new TextButton("", boutonRight);
        tButtonRight.addListener(new ChangeListener() {
                                     @Override
                                     public void changed(ChangeEvent event, Actor actor) {
                                         if(!pause){
                                             oldX = posX;
                                             oldY = posY;
                                             posX += TILE_SIZE_X;
                                             granny.setTexture(granny_east);
                                             if(posX >  camera.position.x + camera.viewportWidth/2- 2*SPRITE_SIZE_X) {
                                                 camera.translate(SPRITE_SIZE_X,0);
                                             }
                                         }


                                     }
                                 }
        );
    }


}
