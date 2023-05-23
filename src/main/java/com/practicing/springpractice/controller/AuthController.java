package com.practicing.springpractice.controller;

import com.practicing.springpractice.dto.RegistrationDto;
import com.practicing.springpractice.model.UserEntity;
import com.practicing.springpractice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserByEmail = userService.findByEmail(user.getEmail());
        if(existingUserByEmail != null
                && existingUserByEmail.getEmail() != null
                && !existingUserByEmail.getEmail().isEmpty()) {
            return "redirect:/registration?fail";
        }

        UserEntity existingUserByUsername = userService.findByUsername(user.getUsername());
        if(existingUserByUsername != null
                && existingUserByUsername.getUsername() != null
                && !existingUserByUsername.getUsername().isEmpty()) {
            return "redirect:/registration?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }

        userService.saveUser(user);
        return "redirect:/clubs/list?success";
    }
}
