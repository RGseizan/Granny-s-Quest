package com.grannysquest.managers.database;

import com.grannysquest.screens.OptionScreen;

import java.util.ArrayList;

public class Difficulte {

    public static int objectifScore =0;
    public static ArrayList<Integer> listScore ;

    public Difficulte(){
        listScore =new ArrayList<Integer>();
    }
    public static int setTemps() {
        int vr=0;

        switch (OptionScreen.valueD) {
            case 1:
                vr =100;
                objectifScore =30;
                break;
            case 2:
                vr =90;
                objectifScore =60;
                break;
            case 3:
                vr =80;
                objectifScore =120;
                break;
            case 4:
                vr =70;
                objectifScore =180;
                break;
            case 5:
                vr =60;
                objectifScore =240;
                break;
            case 6:
                vr =50;
                objectifScore =300;
                break;
            case 7:
                vr =40;
                objectifScore =360;
                break;
            case 8:
                vr =30;
                objectifScore =400;
                break;
            case 9:
                vr =20;
                objectifScore =420;
                break;
            case 10:
                vr =15;
                objectifScore =500;
                break;
            default:

                break;
        }

    return vr;
    }
    public int setBonus() {
        int res=0;
        int vr=0;
        switch (OptionScreen.valueD) {
            case 1:
                vr =10;
                break;
            case 2:
                vr =20;
                break;
            case 3:
                vr =30;
                break;
            case 4:
                vr =40;
                break;
            case 5:
                vr =50;
                break;
            case 6:
                vr =60;
                break;
            case 7:
                vr =70;
                break;
            case 8:
                vr =80;
                break;
            case 9:
                vr =90;
                break;
            case 10:
                vr =100;
                break;
            default:

                break;
        }
         for(int i=0;i<this.listScore.size();i++){
             res+=(this.listScore.get(i)*vr);
         }
         if(res<0){
             res=0;
         }
        return res;
    }
    public static void resetScore(){
        listScore.clear();
    }
}
