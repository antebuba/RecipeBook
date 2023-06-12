package com.example.recipiesbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 500, nullable = false)
    @NotBlank(message = "Molimo unesite ime recepta.")
    String title;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Molimo unesite vrijeme pripreme.")
    String time;

    @Column(length = 30, nullable = false)
    @NotNull(message = "Molimo unesite broj porcija.")
    Integer portions;

    @Column(length =1000, nullable = false)
    @NotBlank(message = "Molimo unesite potrebne sastojke.")
    String ingredients;

    @Column(length =100000, nullable = false)
    @NotBlank(message = "Molimo unesite korake pripreme jela.")
    String steps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
