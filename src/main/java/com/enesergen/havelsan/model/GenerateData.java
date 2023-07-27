package com.enesergen.havelsan.model;

import java.util.ArrayList;
import java.util.Date;

public class GenerateData {
    public static ArrayList<Model> generateData() {
        int counter=0;
        ArrayList<Model>data=new ArrayList<>();
        for(int i=0;i<50;i++){
            data.add(new Model(
                    counter,
                    1,
                    20,
                    27,
                    new double[]{42.0+counter*0.00001,28.0-counter*0.00003,0},
                    new Date()));
        counter++;
        }
        return data;
    }
}
