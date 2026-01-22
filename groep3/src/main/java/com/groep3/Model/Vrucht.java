package com.groep3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vrucht {
    private String categorie;
    private String naam;
    private String herkomst;
    @JsonProperty("beschikbaarheid_nl")
    private long beschikbaarheidNl;
    private String verhaal;
    @JsonProperty("soort_fruit")
    private String soortFruit;
    private Producent producent;
    private Integer percentage;

    public String getCategorie() {
        return categorie;
    }

    public String getNaam() {
        return naam;
    }

    public String getHerkomst() {
        return herkomst;
    }

    public long getBeschikbaarheidNl() {
        return beschikbaarheidNl;
    }

    public String getVerhaal() {
        return verhaal;
    }

    public String getSoortFruit() {
        return soortFruit;
    }

    public Producent getProducent() {
        return producent;
    }

    public Integer getPercentage() {
        return percentage;
    }
}