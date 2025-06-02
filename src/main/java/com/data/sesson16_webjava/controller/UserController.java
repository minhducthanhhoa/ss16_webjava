package com.data.sesson16_webjava.controller;

import com.data.sesson16_webjava.model.User;
import com.data.sesson16_webjava.service.UserService;
import com.data.sesson16_webjava.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService = new UserServiceImpl();

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") @Valid User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.register(user)) {
            return "redirect:/login";
        }

        model.addAttribute("error", "Đăng ký thất bại, có thể do username/email đã tồn tại.");
        return "register";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") @Valid User user,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            if ("ADMIN".equalsIgnoreCase(loginUser.getRole())) {
                model.addAttribute("user", loginUser);
                return "busList";
            } else {
                model.addAttribute("user", loginUser);
                return "home";
            }
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu.");
        return "login";
    }

}
