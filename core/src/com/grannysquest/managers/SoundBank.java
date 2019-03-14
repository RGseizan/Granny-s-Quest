package com.grannysquest.managers;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import static com.badlogic.gdx.Input.Keys.R;

public class SoundBank {
     Music music1;
     Music music2;
     Music music3;
     Music music4;
     Sound bruit1;
     Sound bruit3;
     Sound bruit4;
     Sound bruit5;
     Sound bruit6;
     Sound bruit7;
     Sound bruit8;


    public SoundBank(){
        this.music1 = Gdx.audio.newMusic(Gdx.files.internal("media/Sound/elavator.mp3"));      //< Music fond du menu
        this.music2 = Gdx.audio.newMusic(Gdx.files.internal("media/Sound/musicstage.mp3"));    //< Music fond du jeux
        this.music3 = Gdx.audio.newMusic(Gdx.files.internal("media/Sound/defaite.mp3"));       //< Music gameover
        this.music4 = Gdx.audio.newMusic(Gdx.files.internal("media/Sound/hightscore.mp3"));    //< Music victoire
        this.bruit1 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/heh.mp3"));           //< Noise Degat subit (not used)
        this.bruit3 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/error.mp3"));         //< Noise Message d'erreur
        this.bruit4 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/action.mp3"));        //< Noise bouton action
        this.bruit5 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/bat.mp3"));           //< Noise tout bouton (not used)
        this.bruit6 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/objetramasser.mp3")); //< Noise ramassage d'objet
        this.bruit7 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/fintimer.mp3"));      //< Noise timer presque finit
        this.bruit8 = Gdx.audio.newSound(Gdx.files.internal("media/Sound/ennemistaper.mp3"));  //< Noise dégats infligé (not used)
    }
    public Music choixMusic(String choix){
        Music res =null;

        if ("musicMenu".equals(choix)) {
            res = music1;

        } else if ("musicStage".equals(choix)) {
            res = music2;

        } else if ("musicLoose".equals(choix)) {
            res = music3;

        } else if ("musicWin".equals(choix)) {
            res = music4;
        }
        return res;
    }

    public Sound choixSound(String choix){
        Sound res =null;

         if ("bruitDegatSubit".equals(choix)) {
            res = bruit1;

        }  else if ("bruitBouttonAction".equals(choix)) {
            res = bruit4;

        } else if ("bruitClique".equals(choix)) {
            res = bruit5;

        } else if ("bruitRamassageObjet".equals(choix)) {
            res = bruit6;

        } else if ("bruitTimmerEnd".equals(choix)) {
            res = bruit7;

        } else if ("bruitTaperEnnemie".equals(choix)) {
            res = bruit8;

        } else {
            res = bruit3;
        }
        return res;
    }
    public void volume(String choix,int droiteInt){
        if(choix.contains("music")){
            music1.setVolume( intToFloat(droiteInt));
            music2.setVolume( intToFloat(droiteInt));
            music3.setVolume( intToFloat(droiteInt));
            music4.setVolume( intToFloat(droiteInt));
        }else{
            bruit1.setVolume(bruit1.play(),intToFloat(droiteInt));
            bruit3.setVolume(bruit3.play() ,intToFloat(droiteInt));
            bruit4.setVolume(bruit4.play() ,intToFloat(droiteInt));
            bruit5.setVolume(bruit5.play(),intToFloat(droiteInt));
            bruit6.setVolume(bruit6.play(),intToFloat(droiteInt));
            bruit7.setVolume(bruit7.play() ,intToFloat(droiteInt));
            bruit8.setVolume(bruit8.play() ,intToFloat(droiteInt));
        }

    }
    public void play(String choix){
        if(choix.contains("music"))
        {
            choixMusic(choix).play();
        }else{
            choixSound(choix).play();
        }

    }

    public void pause(String choix){
            System.err.println("game paused");
            choixMusic(choix).pause();
            this.allPausing();
    }


    public void stop(String choix){
        if(choix.contains("music"))
        {
            choixMusic(choix).stop();
        }else{
            choixSound(choix).stop();
        }

    }


    public void loop(String choix){
        if(choix.contains("music"))
        {

                choixMusic(choix).setLooping(true);
                choixMusic(choix).play();


        }
    }
public Boolean allPausing(){
    Boolean res = false;
        for(int i =0 ; i<5;i++) {


                res = true;
                music1.pause();


                res = true;
                music2.pause();


                res = true;
                music3.pause();


                res = true;
                music4.pause();

            System.err.println("game paused"+i);
        }
  return res;
}

    private float intToFloat(int leInt) {
        float res =0.5f;
        switch (leInt){
            case 0:
                res=0f;
                break;
            case 1:
                res=0.1f;
                break;
            case 2:
                res=0.2f;
                break;
            case 3:
                res=0.3f;
                break;
            case 4:
                res=0.4f;
                break;
            case 6:
                res=0.6f;
                break;
            case 7:
                res=0.7f;
                break;
            case 8:
                res=0.8f;
                break;
            case 9:
                res=0.9f;
                break;
            case 10:
                res=1f;
                break;
            default:
                res=0.5f;
                break;
        }
        return res;
    }

}
