package com.grannysquest.managers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.grannysquest.constante.ConstanteDataBase;

/**
 * Class permettant la création de la base de données sur l'android
 *
 * @author thierry
 */
public class UserDataBaseAndroid extends UserDataBase{

    private SQLiteOpenHelper db;
    private SQLiteDatabase stmt;
    private static Context context;
    private String name;

    /**
     * Constructeur avec deux paramètres
     *
     * @param name représente le nom de la table à créer
     * @param version représente la version de la table à créer
     */
    public UserDataBaseAndroid (String name, int version){
        this.name = name;
        db = new AndroidDb(UserDataBaseAndroid.context, this.name, null, version);
        open();
    }

    /**
     * Constructeur avec deux paramètres
     *
     * @param context représente le context de l'android
     * @param name représente le nom de la table à créer
     * @param version représente la version de la table à créer
     */
    public UserDataBaseAndroid (Context context, String name, int version){
        UserDataBaseAndroid.context = context;
        this.name = name;
        db = new AndroidDb(UserDataBaseAndroid.context, this.name, null, version);
        open();
    }

    /**
     * Méthode d'ouverture de la base de données
     */
    public void open(){
        stmt = db.getWritableDatabase();
    }

    /**
     * Méthode de fermeture de la base de données
     */
    public void close(){
        stmt.close();
    }

    /**
     * Méthode pour exécuter une requête sur la base de données
     *
     * @param sql représente la requête
     */
    @Override
    public void execute(String sql) {
        stmt.execSQL(sql);
    }

    /**
     * Méthode pour exécuter une requete sur la base de données
     *
     * @param sql représente la requête
     * @return un entier représentant le nombre de modification effectué
     */
    @Override
    public int executeUpdate(String sql) {
        stmt.execSQL(sql);
        SQLiteStatement tmp = stmt.compileStatement(("SELECT CHANGES()"));
        return (int) tmp.simpleQueryForLong();
    }

    /**
     * Méthode pour récupérer le resultat de la requête
     *
     * @param sql représente la requête
     * @return le résultat de la requête
     */
    @Override
    public Result query(String sql) {
        ResultAndroid result;

        result = new ResultAndroid(stmt.rawQuery(sql,null));
        return result;
    }

    /**
     * Classe permettant de gérer la base de données
     */
    class AndroidDb extends SQLiteOpenHelper {

        /**
         * Constructeur avec quatre paramètres
         *
         * @param context représente le context de l'android
         * @param name représente le nom de la table à créer
         * @param factory représente le cursor factory
         * @param version représente la version de la table à créer
         */
        public AndroidDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * Méthode pour la création de la base de données si celle-ci n'existe pas
         *
         * @param db représente la base de données
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            stmt = db;
            String sql = null;

            if (name.equalsIgnoreCase("user")){
                sql = ConstanteDataBase.USER_TABLE_CREATE;
            }
            UserDataBaseAndroid.this.onCreate(sql);
        }

        /**
         * Méthode de mise à jour de la base de données
         *
         * @param db  représente la base de données
         * @param oldVersion représente l'ancienne version de la base de données
         * @param newVersion représente la nouvelle version de la base de données
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            stmt = db;
            String sql = null;
            String sql2 = null;

            if (name.equalsIgnoreCase("user")){
                sql = ConstanteDataBase.USER_TABLE_DROP;
                sql2 = ConstanteDataBase.USER_TABLE_CREATE;
            }
            UserDataBaseAndroid.this.onUpdate(sql, sql2);
        }
    }
}