package com.example.recipiesbook.controller;

import com.example.recipiesbook.model.Recipe;
import com.example.recipiesbook.model.UserDetails;
import com.example.recipiesbook.repositories.RecipeRepository;
import com.example.recipiesbook.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepo;

    @GetMapping("recipes")
    public String listRecipes (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("userDetails", userDetails);
        List<Recipe> listRecipes = recipeRepo.findAll();
        model.addAttribute("listRecipes", listRecipes);
        model.addAttribute("activeLink", "Recepti");
        return "recipes";
    }

    @GetMapping("recipes/add")
    public String showAddRecipeForm (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Recepti");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("recipe", new Recipe());
        return "add_recipe";
    }

    @PostMapping("recipes/add")
    public String addRecipes (@Valid Recipe recipe, BindingResult result, Model model) {
        boolean errors = result.hasErrors();
        if (errors) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Recepti");
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("recipe", recipe);
            return "add_recipe";
        }
        recipeRepo.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("recipes/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("activeLink", "Recepti");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("recipe", recipe);
        return "edit_recipe";
    }

    @PostMapping("recipes/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Recipe recipe,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            recipe.setId(id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("activeLink", "Recepti");
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("recipe", recipe);
            return "edit_recipe";
        }

        recipeRepo.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        recipeRepo.delete(recipe);
        return "redirect:/recipes";
    }
}