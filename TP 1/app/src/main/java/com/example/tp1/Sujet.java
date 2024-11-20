package com.example.tp1;

public interface Sujet {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void avertirObservateurs();
}