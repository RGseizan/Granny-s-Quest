package com.grannysquest.objet;

import com.grannysquest.App;
import com.grannysquest.constante.ConstanteDataBase;
import com.grannysquest.screens.OptionScreen;

/**
 * Classe gérant les paramètres et l'utilisateur actif
 * afin de les récupérer lors de lancement de l'application
 *
 * @author Thierry
 */
public class Param {

    public static String user;

    /**
     * Méthode permettant de sauvegarder les paramètres actuels
     */
    public static void addTable (){
        App.udb.onDelete(ConstanteDataBase.PARAM_TABLE_DELETE);
        App.udb.onInsert(ConstanteDataBase.PARAM_TABLE_INSERT + "('" + User.actif + "'," + OptionScreen.valueM
                + "," + OptionScreen.valueB + "," + OptionScreen.valueD +")");
    }

    /**
     * Méthode permettant de charger les paramètres
     */
    public static void setValues(){
        String [] tab = App.udb.onSelect(ConstanteDataBase.PARAM_TABLE_SELECT)[0].split(";");
        new User(tab[0]);
        OptionScreen.valueM = Integer.parseInt(tab[1].trim());
        OptionScreen.valueB = Integer.parseInt(tab[2].trim());
        OptionScreen.valueD = Integer.parseInt(tab[3].trim());
    }
}