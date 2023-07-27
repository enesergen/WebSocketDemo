package com.enesergen.havelsan.model;

import java.util.ArrayList;

public class UpdateData {
    public static void updateData(ArrayList<Model> data){
        data.forEach((item)->{
            item.setShipId(item.getShipId()+1);
        });
    }
}
