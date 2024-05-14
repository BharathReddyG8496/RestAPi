package com.bharath.rest.webservices.restfulwebservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
        User user=userDaoService.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id:" + id);
        return user ;
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

//It is important to send the right httpstatus response back to the frontend, so what we need to do is make sure that
// null is returned whenever the user with the id is not found in the database. then using the if condition, if null , then
//throw an exception an instance of a class which u create by extending the RuntimeException class and create a constructor
//accepting a string(which is the message you want to print) and calling the super() with the received string as parameter
// and add the annotation @ResponseStatus(code=HttpResponse.NOT_FOUND).

