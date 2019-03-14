package com.grannysquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.grannysquest.constante.ConstanteDataBase;
import com.grannysquest.managers.SoundBank;
import com.grannysquest.managers.database.Toast;
import com.grannysquest.objet.HighScore;
import com.grannysquest.objet.Param;
import com.grannysquest.objet.User;
import com.grannysquest.screens.MainMenuScreen;
import com.grannysquest.managers.database.UserDataBase;
import com.grannysquest.screens.OptionScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class App extends Game {
    public static SoundBank sb;
    public static UserDataBase udb;
    public static MainMenuScreen mainMenuScreen;
    public static ArrayList<Screen> listScreen = new ArrayList<Screen>();
    private final List<Toast> toasts = new LinkedList<Toast>();
    private Toast.ToastFactory toastFactory;

    /**
     * Constructeur avec un paramètre
     *
     * @param udb base de données créée depuis l'android
     */

    public  App(UserDataBase udb) {
        App.udb = udb;
    }

    @Override
    public void create () {
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);

        udb.onCreate(ConstanteDataBase.PARAM_TABLE_CREATE);
        if (udb.onExist(ConstanteDataBase.PARAM_TABLE_SELECT)){
            udb.onInsert(ConstanteDataBase.PARAM_TABLE_INSERT + "('" + new User("inconnu").getName() + "', 5, 5, 5)");
        }

        Param.setValues();
        new HighScore();

         toastFactory = new Toast.ToastFactory.Builder()
                .font(font)
                .fontColor(new Color(1f, 1f, 1f, 1f))
                .fadingDuration(5f)
                .margin(20)
                .maxTextRelativeWidth(0.5f)
                .positionY(100)
                .build();

        mainMenuScreen = new MainMenuScreen( this );
        listScreen.add(mainMenuScreen);
        setScreen(mainMenuScreen);
        sb= new SoundBank();
    }

    public void toastLong(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.LONG));
    }

    /**
     * Displays short toast
     */
    public void toastShort(String text) {
        toasts.add(toastFactory.create(text, Toast.Length.SHORT));
    }
    @Override
    public void render() {
        super.render();

        // handle toast queue and display
        Iterator<Toast> it = toasts.iterator();
        while(it.hasNext()) {
            Toast t = it.next();
            if (!t.render(Gdx.graphics.getDeltaTime())) {
                it.remove(); // toast finished -> remove
            } else {
                break; // first toast still active, break the loop
            }
        }
    }
}