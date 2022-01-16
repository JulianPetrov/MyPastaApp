package com.f101469.mypastaapp.model;

import java.util.List;

public class PastaApiResponseDTO {
    private List<Pasta> meals;

    public List<Pasta> getMeals() {
        return meals;
    }

    public void setMeals(List<Pasta> meals) {
        this.meals = meals;
    }
}
