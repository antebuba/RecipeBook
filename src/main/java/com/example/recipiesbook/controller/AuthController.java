package com.example.recipiesbook.controller;

import com.example.recipiesbook.model.User;
import com.example.recipiesbook.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String showRegistrationForm (Model model) {
        model.addAttribute("user", new User());
        return "register_form";
    }

    @PostMapping("/register_user")
    public String registerUser (@Valid User user, BindingResult result, Model model) {
        boolean errors = result.hasErrors();
        if (errors) {
            return "register_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasswordRepeat(encodedPassword);
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "registration_success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login_form";
    }


}