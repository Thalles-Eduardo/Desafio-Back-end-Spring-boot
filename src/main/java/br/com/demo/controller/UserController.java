package br.com.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.model.UserModel;
import br.com.demo.service.UserService;
import jakarta.validation.Valid;

@RestController
public class UserController {

    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public List<UserModel> create(@Valid @RequestBody UserModel user){   
        return userService.createAndFindAll(user);
    }

    @PutMapping("/update")
    public List<UserModel> update(@Valid @RequestBody UserModel user) {
        return userService.updateAndFindAll(user);
    }

    @GetMapping("/list")
    public List<UserModel> list() {
        return userService.list();
    }

}
