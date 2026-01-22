package com.groep3.model;

public class Fruit extends Product {

    private String producer;
    private String categorie;
    private String herkomst;
    private String soort;
    private long beschikbaarheid;
    private String boerderij;
    private String streek;
    private String boerBericht;

    public Fruit(String name, String description, String imagePath, double price) {
        super(name, description, imagePath, price);
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getHerkomst() {
        return herkomst;
    }

    public void setHerkomst(String herkomst) {
        this.herkomst = herkomst;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public long getBeschikbaarheid() {
        return beschikbaarheid;
    }

    public void setBeschikbaarheid(long beschikbaarheid) {
        this.beschikbaarheid = beschikbaarheid;
    }

    public String getBoerderij() {
        return boerderij;
    }

    public void setBoerderij(String boerderij) {
        this.boerderij = boerderij;
    }

    public String getStreek() {
        return streek;
    }

    public void setStreek(String streek) {
        this.streek = streek;
    }

    public String getBoerBericht() {
        return boerBericht;
    }

    public void setBoerBericht(String boerBericht) {
        this.boerBericht = boerBericht;
    }
}