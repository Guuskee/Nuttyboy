package com.company;

public enum AkkoordenEnum {
    MAJOR ("Majeur", Akkoorden.majeur),
    MINOR ("Mineur", Akkoorden.mineur),
    AUGMENTED ("Augmented", Akkoorden.aug),
    DIMINISHED ("Diminished", Akkoorden.dim),
    SUS2 ("Suspended 2nd", Akkoorden.sus2),
    SUS4 ("Suspended 4th", Akkoorden.sus4),
    POWER ("Power Chord", Akkoorden.pwCh),
    SEVENTH ("Septiem", Akkoorden.domSeptiem),
    MINSEVENTH ("Mineur Septiem", Akkoorden.minSeptiem),
    MAJSEVENTH ("Majeur Septiem", Akkoorden.majSeptiem),
    MINMAJSEVENTH ("Mineur-Majeur Septiem", Akkoorden.minMajSeptiem);

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
