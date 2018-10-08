package com.company;

public enum LaddersEnum {
    MAJOR("Majeur", Toonladders.majeur),
    MINOR("Mineur", Toonladders.mineur),
    MINPEN("Mineur Pentatonisch", Toonladders.minpen),
    BLUES("Blues", Toonladders.blues);

    private final String naam;
    private final int[] stappen;


    LaddersEnum(String naam, int[] stappen) {
        this.naam = naam;
        this.stappen = stappen;
    }

    public String getNaam() {
        return naam;
    }

    public int[] getStappen() {
        return stappen;
    }
}
