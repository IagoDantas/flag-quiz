package com.example.quizbandeiras;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Questao {
    private String imagePath;
    private List<String> respostas = new ArrayList<>();
    private int respostaCerta;

    public Questao(String imagePath, int respostaCerta, String... respostas){
        this.imagePath = imagePath;
        this.respostas.add(respostas[0]);
        this.respostas.add(respostas[1]);
        this.respostas.add(respostas[2]);
        this.respostas.add(respostas[3]);
        this.respostaCerta = respostaCerta;
    }

    public String getImagePath(){ return this.imagePath; }
    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
    }
    public List<String> getRespostas(){ return this.respostas; }
    public int getRespostaCerta(){ return this.respostaCerta; }
}
