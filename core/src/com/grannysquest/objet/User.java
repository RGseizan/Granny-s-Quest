package com.grannysquest.objet;

import com.grannysquest.App;
import com.grannysquest.constante.ConstanteDataBase;

/**
 * Classe qui permet la gestion des utilisateurs
 *
 * @author Thierry
 */
public class User {

    private String name;
    public static String actif;
    public static User userActif;

    /**
     * Constructeur avec un paramètre
     *
     * @param name correspond au nom de l'utilisateur
     */
    public User (String name){
        this.name = name;
        actif = this.name;
        userActif = this;
        addDataBaseUser();
        Param.addTable();
    }

    /**
     * Méthode pour l'ajout de l'utilisateur dans la base de données des utilisateurs
     */
    private void addDataBaseUser(){

        if (App.udb.onExist("SELECT " + ConstanteDataBase.COLUMN_NAME + " FROM " + ConstanteDataBase.USER_TABLE_NAME +
                " WHERE " + ConstanteDataBase.COLUMN_NAME + " = '" + name + "'")){
            App.udb.onInsert(ConstanteDataBase.INSET_INTO_USER  + "('" + name + "')");
            createDataBaseTable();
            System.err.println("utilisateur créer");
        }
        else {
            System.err.println("utilisateur existe");
        }
    }

    /**
     * Méthode pour la suppression d'un utilisateur dans la base de données
     *
     * @param name correspond au non de l'utilisateur
     */
    public void deleteDateBaseUser(String name){
        App.udb.onDelete("DELETE FROM " + ConstanteDataBase.USER_TABLE_NAME
                + " WHERE " + ConstanteDataBase.COLUMN_NAME + " = '" + name + "'");
        App.udb.onDrop ("DROP TABLE " + name + "_" + ConstanteDataBase.HIGHSCORE_TABLE_NAME);
    }

    /**
     *
     */
    private void createDataBaseTable(){
        App.udb.onCreate("CREATE TABLE " + this.name + "_" + ConstanteDataBase.HIGHSCORE_TABLE_NAME + " ("
            + ConstanteDataBase.COLUMN_SCORE + " INTEGER NOT NULL);");
    }

    /**
     * Méthode pour l'ajout d'un score sur les deux bases de données
     *
     * @param score correspond au score à ajouter
     */
    public void addScoreDataBase(Integer score){
        App.udb.onInsert("INSERT INTO " + name + "_" + ConstanteDataBase.HIGHSCORE_TABLE_NAME + " (score) VALUES  (" + score + ");");
        App.udb.onInsert(ConstanteDataBase.HIGHSCORE_ALL_TABLE_INSERT + "('" + this.name + "'," + score + ")");
        HighScore.deleteScore();
    }

    /**
     * Méthode pour la récupération des scores du joueur
     *
     * @return un tableau de string contenant tout les scores du joueur
     */
    public String [] getScore(){
        return App.udb.onSelect("SELECT " + ConstanteDataBase.COLUMN_SCORE + " FROM " + name + "_"
                + ConstanteDataBase.HIGHSCORE_TABLE_NAME + " ORDER BY " + ConstanteDataBase.COLUMN_SCORE + " DESC");
    }

    /**
     * Méthode pour la récupération des noms
     *
     * @return
     */
    public String getName() {
        return name;
    }
}