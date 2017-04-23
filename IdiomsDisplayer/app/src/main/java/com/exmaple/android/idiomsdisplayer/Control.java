package com.exmaple.android.idiomsdisplayer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by emmettna on 15/4/17.
 */

public class Control  {
    ArrayList<String> list;
    InputStream input;

    Control(){
        list = new ArrayList<>();
//        AssetManager assetManager = getAssets();
        input = this.getClass().getClassLoader().getResourceAsStream("res/idioms.txt");

        Scanner sc = null;
        sc = new Scanner("Gday");
//        while (sc.hasNext()) {
//            String line = sc.nextLine();
//            String[] array = line.split(":");
//            String english = array[0];
//            String korean = array[1];
//            list.add(english);
//            list.add(korean);
//            System.out.println(list);
//        }

    }



}
