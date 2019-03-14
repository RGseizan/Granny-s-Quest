package com.grannysquest.managers.database;

public abstract class UserDataBase {

    protected static UserDataBase instance = null;

    /**
     * Méthode de création de requête
     *
     * @param sql représente la requête
     */

    public abstract void execute(String sql);

    /**
     * Méthode de création de requete
     *
     * @param sql représente la requête
     * @return retourne un entier du nombre de changement
     */

    public abstract int executeUpdate(String sql);

    /**
     * Méthode de récupération de résultat
     *
     * @param sql représente la requête
     * @return un Result
     */

    public abstract Result query(String sql);

    /**
     * Méthode de création d'un table
     *
     * @param sql représente la requête
     */

    public void onCreate (String sql) {
        execute(sql);
    }

    /**
     * Méthode de suppression d'un table
     *
     * @param sql représente la requête
     */

    public void onDrop (String sql){
        execute(sql);
    }

    /**
     * Méthode de mise à jour de la base de données
     *
     * @param sql représente la requête1
     * @param sql2 représente la requête
     */

    public void onUpdate (String sql, String sql2){
        execute(sql2);
        onCreate(sql);
    }

    /**
     * Méthode d'ajout de données dans la base
     *
     * @param sql représente la requête
     */

    public void onInsert (String sql){
        execute(sql);
    }

    /**
     * Méthode qui permet la suppression d'un utilisateur
     *
     * @param sql
     */

    public void onDelete(String sql){
        execute(sql);
    }

    /**
     * Méthode pour la vérification d'un personne dans la base de données
     *
     * @param sql représente la requête
     * @return un boolean
     */

    public boolean onExist(String sql){
        Result q = query(sql);
        return q.isEmpty();
    }

    /**
     * Méthode qui permet la récupération sous forme de tableau de string
     *
     * @param sql représente la requête
     * @return un tableau de String
     */

    public String[] onSelect(String sql){

        Result q = query(sql);
        String[] result;

        // Condition dans le cas d'un resultat non vide
        if (!q.isEmpty()){
            StringBuilder sb = new StringBuilder();
            result = new String[q.getCount()];

            // Boucle pour parcourir les différentes lignes
            for (int i = 0; i < q.getCount(); i++) {
                q.moveToNext();
                for (int j = 0; j < q.getColumnCount(); j++) {
                    sb.append(q.getString(j) + "; ");
                }
                sb.deleteCharAt(sb.lastIndexOf(";"));
                result[i] = sb.toString();
                sb.delete(0, sb.length());
            }
            return result ;
        }

        result = new String [1];
        result [0] = "Pas de score";
        return result ;
    }
}