package com.project6_dzikir;

public class Dzikir {
    private String arab;
    private String latin;
    private String terjemah;
    private int jumlah;
    private String audio;

    public Dzikir(String arab, String latin, String terjemah, int jumlah, String audio) {
        this.arab = arab;
        this.latin = latin;
        this.terjemah = terjemah;
        this.jumlah = jumlah;
        this.audio = audio;
    }

    public String getArab() { return arab; }
    public String getLatin() { return latin; }
    public String getTerjemah() { return terjemah; }
    public int getJumlah() { return jumlah; }
    public String getAudio() { return audio; }
}
