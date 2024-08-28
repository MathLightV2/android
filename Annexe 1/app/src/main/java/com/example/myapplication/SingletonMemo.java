package com.example.myapplication;

import android.content.Context;

import java.security.PrivateKey;
import java.util.ArrayList;

public class SingletonMemo {

    private static SingletonMemo instance;
    private ArrayList<String> listeMemo;
    private Context context;


    public static SingletonMemo getInstance(Context context) {
        if (instance == null){
            instance = new SingletonMemo(context);
        }
        return instance;

    }

    private SingletonMemo(Context context){
        this.context = context;
        listeMemo = new ArrayList<String>();

    }

    public void ajouteMemo(String memo){
        listeMemo.add(memo);
    }

    public ArrayList<String> getListeMemo() {
        return listeMemo;
    }
}