package com.tdozo.vlp.enums;

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
}
