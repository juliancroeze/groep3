package com.groep3.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Producent {
    private String boer;
    private String boerderij;
    @JsonProperty("provincie_of_streek")
    private String provincieOfStreek;
    @JsonProperty("boer_bericht")
    private String boerBericht;

    public String getBoer() { return boer; }
    public String getBoerderij() { return boerderij; }
    public String getProvincieOfStreek() { return provincieOfStreek; }
    public String getBoerBericht() { return boerBericht; }
}