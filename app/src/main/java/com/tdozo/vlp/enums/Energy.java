package com.tdozo.vlp.enums;

import android.content.Context;

import com.tdozo.vlp.R;

public enum Energy {
    ENERGETIC(R.string.ENERGETIC),
    RESTED(R.string.RESTED),
    TIRED(R.string.TIRED),
    FATIGATED(R.string.FATIGATED),
    EXHAUSTED(R.string.EXHAUSTED);

    private final int name;

    Energy(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public static String[] getNames(Context r) {
        String[] aux = new String[Energy.values().length];
        for (int i = 0; i < Energy.values().length; i++) {
            aux[i] = r.getString(Energy.values()[i].getName());
        }
        return aux;
    }
}
