package com.exmaple.android.idiomsdisplayer;

import java.util.ArrayList;

/**
 * Created by emmettna on 16/4/17.
 */
/* Temporary File with ArrayList of idioms for testing purpose*/
public class Temporary1 {
    ArrayList<String> list;
    Temporary1(){
        list = new ArrayList<>();
        list.add("A bird in the hand is worth two in the bush");
        list.add("구슬이 서 말이라도 꿰어야 보배다");
        list.add("A Burnt child dreads the fire");
        list.add("자라 보고 놀란 가슴 솥뚜껑 보고도 놀란다");
        list.add("A friend in need is a friend indeed");
        list.add("어려울때 친구가 진짜 친구다");
    }
    public ArrayList<String> getList(){
        return list;
    }
}
