package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/newUser";
        } else {
            userService.save(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "admin/editUser";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/editUser";
        } else {
            userService.updateUser(user);
            return "redirect:/admin";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
