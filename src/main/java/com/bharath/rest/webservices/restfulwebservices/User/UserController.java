package com.bharath.rest.webservices.restfulwebservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> returnAllUsers(){
        return  userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User returnUser(@PathVariable int id){
           return  userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> AddUser(@RequestBody User user){
       User savedUser= userDaoService.SaveUser(user);
        URI url=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(url).build();
    }

    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id){
        userDaoService.DeleteUser(id);
    }


}
