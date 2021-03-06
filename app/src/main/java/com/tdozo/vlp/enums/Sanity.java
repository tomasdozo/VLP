package com.tdozo.vlp.enums;

import android.content.Context;

import com.tdozo.vlp.R;

public enum Sanity {
    SANE(R.string.SANE_name, R.string.SANE_description),
    NERVOUS(R.string.NERVOUS_name, R.string.NERVOUS_description),
    UNSTABLE(R.string.UNSTABLE_name, R.string.UNSTABLE_description),
    SHOCKED(R.string.SHOCKED_name, R.string.SHOCKED_description),
    CRAZY(R.string.CRAZY_name, R.string.CRAZY_description);

    private final int name;
    private final int description;

    Sanity(int name, int description) {
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
        String[] aux = new String[Sanity.values().length];
        for (int i = 0; i < Sanity.values().length; i++) {
            aux[i] = r.getString(Sanity.values()[i].getName());
        }
        return aux;
    }
}

