package com.groep3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FruitMandenLijst {

    @JsonProperty("fruitmanden")
    private List<FruitMand> fruitmanden;

    public List<FruitMand> getFruitmanden() {
        return fruitmanden;
    }
}