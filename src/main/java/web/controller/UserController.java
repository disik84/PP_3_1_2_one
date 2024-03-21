package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImp;

import java.util.List;

@Controller
public class UserController {
    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping(value = "/")
    public String showIndexPage(ModelMap model) {
        model.addAttribute("listUsers", userServiceImp.findAllUser());
        return "index";
    }

    @GetMapping(value = "/add-user")
    public String addUser(ModelMap model) {
        return "add-user";
    }

    @GetMapping(value = "/show-info-new-user")
    public String showInfoNewUser(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "lastname", required = false) String lastname,
                                  @RequestParam(value = "age", required = false) byte age,
                                  ModelMap model) {
        User user = new User(name, lastname, age);
        userServiceImp.addUser(user);
        model.addAttribute("user", user);
        return "show-info-new-user";
    }

    @GetMapping(value = "/show-info-delete-user")
    public String showInfoDeletedUser(@RequestParam(value = "id", required = false) Long id,
                                      ModelMap model) {
        userServiceImp.deleteUser(id);
        return "show-info-delete-user";
    }

    @GetMapping(value = "/edit-user")
    public String editUser(@RequestParam(value = "id", required = false) Long id,
                           ModelMap model) {
        User user = userServiceImp.getUserById(id);
        if (user.getId() == null) {
            return "user-not-found";
        }
        model.addAttribute("user", user);
        return "edit-user";
    }

    @GetMapping(value = "/show-info-edit-user")
    public String showInfoEditUser(@RequestParam(value = "id", required = false) Long id,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "lastname", required = false) String lastName,
                                   @RequestParam(value = "age", required = false) byte age,
                                   ModelMap model) {
        User user = new User(name, lastName, age);
        userServiceImp.editUser(id, name, lastName, age);
        return "show-info-edit-user";
    }
}
