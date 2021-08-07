package com.tdozo.vlp.enums;

import android.content.Context;

import com.tdozo.vlp.R;

public enum Aptitude {
    VIG(R.string.VIG_name, R.string.VIG_description),
    LOG(R.string.LOG_name, R.string.LOG_description),
    PRE(R.string.PRE_name, R.string.PRE_description);


    private final int name;


    private final int description;

    Aptitude(int name, int description) {
        this.name = name;
        this.description = description;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public static String[] getNames(Context r) {
        String[] aux = new String[Aptitude.values().length];
        for (int i = 0; i < Aptitude.values().length; i++) {
            aux[i] = r.getString(Aptitude.values()[i].getName());
        }
        return aux;
    }
}
