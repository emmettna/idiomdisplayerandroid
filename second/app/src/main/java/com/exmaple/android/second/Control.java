package com.exmaple.android.second;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by emmettna on 15/4/17.
 */

class Control  {
    private ArrayList<String> list;

    Control(){
        list = new ArrayList<>();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("assets/idioms.txt");
        Scanner sc = new Scanner(input);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] array = line.split(":");
            String english = array[0];
            String korean = array[1];
            list.add(english);
            list.add(korean);
        }
        sc.close();

    }
    ArrayList<String> getList(){
        return list;
    }



}
