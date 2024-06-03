package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.createNewUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") long id, Model model) {
        if (userService.getUserById(id).isPresent()) {
            model.addAttribute("user", userService.getUserById(id).get());
            return "update";
        }
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUserOption(@ModelAttribute("user") User user,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String removeUser(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}

