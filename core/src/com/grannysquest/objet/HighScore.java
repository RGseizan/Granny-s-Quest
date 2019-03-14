package com.grannysquest.objet;

import com.grannysquest.App;
import com.grannysquest.constante.ConstanteDataBase;


/**
 * Classe pour les HighScore général
 *
 * @author Thierry
 */
public class HighScore {

    /**
     * Constructeur par défaut
     */
    public HighScore() {
        createHighScore();
    }

    /**
     * Méthode pour la création de la base de données si elle n'existe pas
     */
    private void createHighScore(){
        App.udb.onCreate(ConstanteDataBase.HIGHSCORE_ALL_TABLE_CREATE);
        App.udb.onInsert(ConstanteDataBase.HIGHSCORE_ALL_TABLE_INSERT + "('" + User.actif + "', " + 0 + ")");
    }

    /**
     * Méthode pour l'ajout du score dans la base de données
     *
     * @param score correspond au score du joueur
     */
    public static void addScore(int score){
        App.udb.onInsert(ConstanteDataBase.HIGHSCORE_ALL_TABLE_INSERT + "('" + User.actif + "', " + score + ")");
    }

    /**
     * Méthode pour la récupération de la base complète
     *
     * @return
     */
    public static String [] getScore(){
        return App.udb.onSelect("SELECT * FROM " + ConstanteDataBase.HIGHSCORE_ALL_TABLE_NAME
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
                    App.udb.onDelete(ConstanteDataBase.HIGHSCORE_ALL_TABLE_DELETE + tab[i].substring((tab[i].indexOf(";") + 1)).trim());
                }
            }
        }
    }

    public static int getHighScore (){
        String [] tab = getScore();
        return Integer.parseInt((tab[0].substring(tab[0].indexOf(";")+ 1).trim()));
    }
}