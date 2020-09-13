package com.foyhWebAppApi.dudoanCK.model;



import org.springframework.stereotype.Repository;

import java.util.ArrayList;
//1 ngay co the co nhieu bieu hien nen bieu hien khi them vao ton tai o dang mang



public class dayBH {
    private ArrayList<String> BH= new ArrayList<>();

    public void addBh(String[] bh){
        for(int i=0;i<=bh.length-1;i++){
            this.BH.add(bh[i]);
        }
    }

    public ArrayList<String> getBH() {
        return BH;
    }

    @Override
    public String toString() {
        return  BH +"";
    }
}
