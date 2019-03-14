package com.grannysquest.objet;

import com.grannysquest.App;
import com.grannysquest.constante.ConstanteDataBase;
import java.text.SimpleDateFormat;

/**
 * Classe pour la gestion des scores
 *
 * @author Thierry
 */
public class Score {

    private int score;

    /**
     * Constructeur avec deux paramètres
     *
     * @param score correspond au nombre d'objet récupérer
     */
    public Score (int score){
        this.score = score;
        addScore(this.score);
    }

    /**
     * Méthode pour l'ajout du score dans la base de données
     *
     * @param score correspond au score du joueur
     */
    private void addScore(int score){
        App.udb.onInsert("INSERT INTO " + User.actif.trim() + "_" + ConstanteDataBase.HIGHSCORE_TABLE_NAME
                + " (" + ConstanteDataBase.COLUMN_SCORE + ") VALUES " + "(" + score + ")");
    }

    /**
     * Méthode pour la récupération de la liste des scores
     *
     * @return un tableau de String contenant les lignes de la base de données
     */
    public static String [] getScore(){
        return App.udb.onSelect("SELECT * FROM " + User.actif +  "_" +  ConstanteDataBase.HIGHSCORE_ALL_TABLE_NAME
                + " ORDER BY " + ConstanteDataBase.COLUMN_SCORE + " DESC");
    }

    /**
     * Méthode pour limiter la base de données à 10 scores
     */
    public static void deleteScore(){
        String [] tab = getScore();
        if (tab.length > 10){
            for (int i = 10; i < tab.length; i++){
                if (tab[i] != tab[9]) {
                    App.udb.onDelete("DELETE FROM " + User.actif +  "_" + ConstanteDataBase.HIGHSCORE_ALL_TABLE_NAME
                            + " WHERE " + ConstanteDataBase.COLUMN_SCORE + " = " + tab[i].substring((tab[i].indexOf(";") + 1)).trim());
                }
            }
        }
    }
}