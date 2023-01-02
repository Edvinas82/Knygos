package model;

public class Knyga {

    private int id;
    private String pavadinimas;
    private int puslapiuSkaicius;
    private String leidejas;
    private double ivertinimas;

    public Knyga(String pavadinimas) {

    }

    public Knyga(int id, String pavadinimas, int puslapiuSkaicius, String leidejas, double ivertinimas) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.puslapiuSkaicius = puslapiuSkaicius;
        this.leidejas = leidejas;
        this.ivertinimas = ivertinimas;
    }

    public Knyga() {

    }

    public void setPavadinimas() {
    }

    public void setId(int id) {
    }

    public void setPavadinimas(String pavadinimas) {
    }

    public void add(Knyga knyga) {
    }

    @Override
    public String toString() {
        return "Knyga{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", puslapiuSkaicius=" + puslapiuSkaicius +
                ", leidejas='" + leidejas + '\'' +
                ", ivertinimas=" + ivertinimas +
                '}';
    }
}

