package com.tdozo.vlp.enums;

import android.content.Context;

import com.tdozo.vlp.R;

public enum Health {
    HEALTHY(R.string.HEALTHY_name, R.string.HEALTHY_description),
    INJURED(R.string.INJURED_name, R.string.INJURED_description),
    CRIPPLED(R.string.CRIPPLED_name, R.string.CRIPPLED_description),
    UNCONSCIOUS(R.string.UNCONSCIOUS_name, R.string.UNCONSCIOUS_description),
    DEAD(R.string.DEAD_name, R.string.DEAD_description);

    private final int name;
    private final int description;

    Health(int name, int description) {
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
        String[] aux = new String[Health.values().length];
        for (int i = 0; i < Health.values().length; i++) {
            aux[i] = r.getString(Health.values()[i].getName());
        }
        return aux;
    }
}

