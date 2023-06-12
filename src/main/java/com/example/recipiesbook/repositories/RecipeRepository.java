package com.example.recipiesbook.repositories;

import com.example.recipiesbook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
}
