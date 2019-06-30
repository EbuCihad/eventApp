package com.example.ebuci.etkinlik;

import android.graphics.Bitmap;

public class globals {

    private Bitmap degistirilenBitmap;
    private static globals instance;

    private  globals()
    {


    }
    public  void  setData(Bitmap degistirilenBitmap)
    {
        this.degistirilenBitmap=degistirilenBitmap;
    }

    public Bitmap getData() {
         return this.degistirilenBitmap;
    }
    public static globals getInstance()
    {
       if(instance==null)
       {
           instance=new globals();
       }
       return instance;
    }
}
