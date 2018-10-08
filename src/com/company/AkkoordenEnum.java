package com.company;

public enum AkkoordenEnum {
    MAJOR ("Majeur", Akkoorden.majeur ),
    MINOR ("Mineur", Akkoorden.mineur ),
    POWER ("Power Chord", Akkoorden.pwch ),
    SEVENTH ("Septiem", Akkoorden.seventh );

    private final String naam;
    private final int[] noten;


    AkkoordenEnum(String naam, int[] noten) {
        this.naam = naam;
        this.noten = noten;
    }

    public String getNaam() {
        return naam;
    }

    public int[] getNoten() {
        return noten;
    }
}
