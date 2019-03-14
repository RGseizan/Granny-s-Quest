package com.grannysquest.managers.database;

/**
 * Interface pour la gestion du resultat
 *
 * @author thierry
 */

public interface Result {

    /**
     * Méthode pour vérifier si la base de données est vide
     *
     * @return un boolean
     */

    boolean isEmpty();

    /**
     * Méthode pour récupérer le nombre de ligne de résultat
     *
     * @return un int
     */

    int getCount();

    /**
     * Méthode pour récupérer le nombre de colonne
     *
     * @return un int
     */

    int getColumnCount();

    /**
     * Méthode pour aller à la ligne suivant
     *
     * @return
     */

    boolean moveToNext();

    /**
     * Méthode pour récupérer l'index d'un colonne
     *
     * @param name représente le nom de la colonne
     * @return un int
     */

    int getColumnIndex (String name);

    /**
     * Méthode pour récupérer la valeur d'un ligne en fonction
     * de la colonne
     *
     * @param ColumnIndex représente l'index de la colonne
     * @return un String
     */

    String getString (int ColumnIndex);
}