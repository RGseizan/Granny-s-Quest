package com.grannysquest.managers.database;

import android.database.Cursor;

/**
 * Class permettant de récupérer des informations du résultat
 *
 * @author thierry
 */

public class ResultAndroid implements Result {
    Cursor cursor;

    /**
     * Constructeur avec un parametre
     *
     * @param cursor représente le curseur de résultat
     */
    public ResultAndroid(Cursor cursor) { this.cursor=cursor; }

    /**
     * Méthode pour vérifier si la base de données est vide
     *
     * @return un boolean
     */
    @Override
    public boolean isEmpty() { return cursor.getCount()==0;}

    /**
     * Méthode pour récupérer le nombre de ligne de résultat
     *
     * @return un int
     */
    @Override
    public int getCount() { return cursor.getCount(); }

    /**
     * Méthode pour récupérer le nombre de colonne
     *
     * @return un int
     */
    @Override
    public int getColumnCount() { return cursor.getColumnCount(); }

    /**
     * Méthode pour aller à la ligne suivant
     *
     * @return
     */
    @Override
    public boolean moveToNext() { return cursor.moveToNext(); }

    /**
     * Méthode pour récupérer l'index d'un colonne
     *
     * @param name représente le nom de la colonne
     * @return un int
     */
    @Override
    public int getColumnIndex(String name) { return cursor.getColumnIndex(name); }

    /**
     * Méthode pour récupérer la valeur d'un ligne en fonction
     * de la colonne
     *
     * @param ColumnIndex représente l'index de la colonne
     * @return un String
     */
    @Override
    public String getString(int ColumnIndex) { return cursor.getString(ColumnIndex); }
}