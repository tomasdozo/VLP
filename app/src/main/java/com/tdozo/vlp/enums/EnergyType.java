package com.tdozo.vlp.enums;

import android.content.Context;

import com.tdozo.vlp.R;

public enum EnergyType {
    VITAL(R.string.VITAL_name, R.string.VITAL_description),
    FURY(R.string.FURY_name, R.string.FURY_description),
    DIVINE(R.string.DIVINE_name, R.string.DIVINE_description),
    BLOOD(R.string.BLOOD_name, R.string.BLOOD_description),
    ACUATIC(R.string.ACUATIC_name, R.string.ACUATIC_description),
    AIR(R.string.AIR_name, R.string.AIR_description),
    MEDITATION(R.string.MEDITATION_name, R.string.MEDITATION_description);

    private final int name;
    private final int description;

    EnergyType(int name, int description) {
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
        String[] aux = new String[EnergyType.values().length];
        for (int i = 0; i < EnergyType.values().length; i++) {
            aux[i] = r.getString(EnergyType.values()[i].getName());
        }
        return aux;
    }
}
