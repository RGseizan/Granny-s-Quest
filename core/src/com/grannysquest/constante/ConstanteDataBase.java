package com.grannysquest.constante;

import com.grannysquest.objet.User;

public class ConstanteDataBase {

    /**
     *  Déclaration des variables dans les requêtes
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    /**
     * Déclaration des noms des colonnes
     */

    public static final String COLUMN_BRUITAGE = "bruitage";
    public static final String COLUMN_DIFFICULTE = "difficulte";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_VOLUME = "volume";

    /**
     * Déclaration des requêtes pour la base de données system
     */
    public static final String PARAM_TABLE_NAME = "Param";
    public static final int PARAM_TABLE_VERSION = 1;
    public static final String PARAM_TABLE_CREATE = CREATE_TABLE + PARAM_TABLE_NAME
            + " (" + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_VOLUME + " INTEGER NON NULL,"
            + COLUMN_BRUITAGE + " INTEGER NON NULL," + COLUMN_DIFFICULTE + " INTEGER NON NULL);";
    public static final String PARAM_TABLE_SELECT = "SELECT * FROM " + PARAM_TABLE_NAME;
    public static final String PARAM_TABLE_INSERT = "INSERT INTO " + PARAM_TABLE_NAME
            + " (" + COLUMN_NAME + ", " + COLUMN_VOLUME + ", " + COLUMN_BRUITAGE
            + ", " + COLUMN_DIFFICULTE + ") VALUES ";
    public static final String PARAM_TABLE_DELETE = "DELETE FROM " + PARAM_TABLE_NAME;

    /**
     * Déclaration des requêtes pour la base de données USER
     */
    public static final String USER_TABLE_NAME = "User";
    public static final int USER_TABLE_VERSION= 01;
    public static final String USER_TABLE_CREATE = CREATE_TABLE + USER_TABLE_NAME
            + " (" + COLUMN_NAME + " TEXT NOT NULL);";
    public static final String USER_TABLE_DROP = DROP_TABLE + USER_TABLE_NAME + ";";
    public static final String INSET_INTO_USER = "INSERT INTO " + USER_TABLE_NAME + " (name) VALUES ";
    public static final String USER_TABLE_SELECT_ALL = "SELECT * FROM " + USER_TABLE_NAME;

    /**
     * Déclaration des requêtes pour la base de données HIGH_SCORE
     */
    public static final String HIGHSCORE_ALL_TABLE_NAME = "High_Score";
    public static final int HIGHSCORE_ALL_TABLE_VERSION= 01;
    public static final String HIGHSCORE_ALL_TABLE_CREATE = CREATE_TABLE + HIGHSCORE_ALL_TABLE_NAME
            + " (" + COLUMN_USER + " TEXT NOT NULL, " + COLUMN_SCORE + " INTEGER NOT NULL);";
    public static final String HIGHSCORE_ALL_TABLE_DROP = DROP_TABLE + HIGHSCORE_ALL_TABLE_NAME + ";";
    public static final String HIGHSCORE_ALL_TABLE_INSERT = "INSERT INTO " + HIGHSCORE_ALL_TABLE_NAME
            + " (" + COLUMN_USER + ", " + COLUMN_SCORE + ") VALUES ";
    public static final String HIGHSCORE_ALL_TABLE_SELECT_ALL = "SELECT * FROM " + HIGHSCORE_ALL_TABLE_NAME;
    public static final String HIGHSCORE_ALL_TABLE_DELETE = "DELETE FROM " + HIGHSCORE_ALL_TABLE_NAME
            + " WHERE " + COLUMN_SCORE + " = ";

    /**
     *  Déclaration des requêtes pour la base de données SCORE
     */
    public static final String HIGHSCORE_TABLE_NAME = "High_Score";
    public static final int HIGHSCORE_TABLE_VERSION= 01;
    public static final String HIGHSCORE_TABLE_DROP = DROP_TABLE + HIGHSCORE_TABLE_NAME + ";";
}