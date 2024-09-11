package com.example.myapplication;

import android.content.Context;

import java.security.PrivateKey;
import java.util.ArrayList;

public class SingletonMemo {

    private static SingletonMemo instance;
    private ArrayList<Memo> listeMemo;
    private Context context;


    public static SingletonMemo getInstance(Context context) {
        if (instance == null){
            instance = new SingletonMemo(context);
        }
        return instance;

    }

    private SingletonMemo(Context context){
        this.context = context;
        listeMemo = new ArrayList<Memo>();

    }

    public void ajouteMemo(Memo m){
        listeMemo.add(m);
    }

    public ArrayList<Memo> getListeMemo() {
        return listeMemo;
    }
}