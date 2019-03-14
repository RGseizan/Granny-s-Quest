package com.grannysquest.objet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Classe pour la création d'un police
 *
 * @author Thierry & Roman
 */
public class PoliceGameplay {

    private BitmapFont fontGameplay_blanche;
    private BitmapFont fontGameplay_noire;
    private BitmapFont fontGameplay_jaune;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter2;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter3;

    /**
     * Constructeur par défaut
     */
    public PoliceGameplay () {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("polices/Gameplay.ttf"));
        setPoliceBlack();
        setPoliceWhite();
        setPoliceJaune();
    }

    /**
     * Méthode pour implémenter les paramètres de la police de couleur blanche
     */
    private void setPoliceWhite(){
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.color.r = 1f;
        parameter.color.g = 1f;
        parameter.color.b = 1f;
        fontGameplay_blanche = generator.generateFont(parameter);
    }

    /**
     * Méthode pour implémenter les paramètres de la police de couleur noire
     */
    private void setPoliceBlack(){
        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 32;
        parameter2.color.r = 0f;
        parameter2.color.g = 0f;
        parameter2.color.b = 0f;
        fontGameplay_noire = generator.generateFont(parameter2);
    }

    /**
     * Méthode pour implémenter les paramètres de la police de couleur jaune
     */
    private void setPoliceJaune(){
        parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.size = 32;
        parameter3.color.r = 0.929f;
        parameter3.color.g = 0.882f;
        parameter3.color.b = 0.113f;
        fontGameplay_jaune = generator.generateFont(parameter3);
    }

    /**
     * Méthode pour la modification de la taille
     *
     * @param size
     */
    public void editSize(int size, String color){

        if (color.equalsIgnoreCase("black")){
            parameter2.size = size;
            fontGameplay_noire = generator.generateFont(parameter2);
        }else if(color.equalsIgnoreCase("white")) {
            parameter.size = size;
            fontGameplay_blanche = generator.generateFont(parameter);
        }else if(color.equalsIgnoreCase("yellow")){
            parameter3.size = size;
            fontGameplay_jaune = generator.generateFont(parameter3);
        }
    }

    public BitmapFont getFontGameplay_blanche() {
        return fontGameplay_blanche;
    }

    public BitmapFont getFontGameplay_noire() {
        return fontGameplay_noire;
    }

    public BitmapFont getFontGameplay_jaune() {
        return fontGameplay_jaune;
    }
}