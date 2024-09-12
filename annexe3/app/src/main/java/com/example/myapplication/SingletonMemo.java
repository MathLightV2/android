package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Objects;

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

    public void  serializerListe () throws IOException {
        // try with resource
        FileOutputStream fos = context.openFileOutput("fichier.ser",MODE_PRIVATE);
        // buffer
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listeMemo);


    }

    public ArrayList<Memo>  deSerializerListe () throws IOException {
        // try with resource
        try (FileInputStream fis = context.openFileInput("fichier.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.listeMemo = (ArrayList<Memo>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this.listeMemo;


    }
}