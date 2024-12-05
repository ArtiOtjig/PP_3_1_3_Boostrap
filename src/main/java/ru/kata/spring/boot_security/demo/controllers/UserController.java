package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/allUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/newUser";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "users/editUser";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/editUser";
        } else {
            userService.updateUser(user);
            return "redirect:/users";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
