package com.tdozo.vlp.enums;

import com.tdozo.vlp.R;

public enum Race {
    HUMAN(R.string.HUMAN_name, R.string.HUMAN_description),
    DWARF(R.string.DWARF_name, R.string.DWARF_description),
    HOBBIT(R.string.HOBBIT_name, R.string.HOBBIT_description),
    FOREST_ELF(R.string.FOREST_ELF_name, R.string.FOREST_ELF_description),
    HIGH_ELF(R.string.HIGH_ELF_name, R.string.HIGH_ELF_description),
    DARK_ELF(R.string.DARK_ELF_name, R.string.DARK_ELF_description),
    HALF_ELF(R.string.HALF_ELF_name,R.string.HALF_ELF_description),
    ORC(R.string.ORC_name, R.string.ORC_description),
    HALF_ORC(R.string.HALF_ORC_name, R.string.HALF_ORC_description),
    NEFILIM(R.string.NEFILIM_name, R.string.NEFILIM_description),
    INFERNAL(R.string.INFERNAL_name, R.string.INFERNAL_description),
    ANDEAN(R.string.ANDEAN_name,R.string.ANDEAN_description),
    TEZCA(R.string.TEZCA_name, R.string.TEZCA_description),
    YARA(R.string.YARA_name,R.string.YARA_description),
    REPTILIAN(R.string.REPTILIAN_name, R.string.REPTILIAN_description),
    CAT_MAN(R.string.CAT_MAN_name, R.string.CAT_MAN_description),
    TRITON(R.string.TRITON_name, R.string.TRITON_description),
    BUGBEAR(R.string.BUGBEAR_name, R.string.BUGBEAR_description),
    PRETORIAN(R.string.PRETORIAN_name, R.string.PRETORIAN_desciption),
    GOBLIN(R.string.GOBLIN_name, R.string.GOBLIN_description);


    private final int name;
    private final int description;

    Race(int name, int description) {
        this.name = name;
        this.description = description;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }
}
