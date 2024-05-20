package com.bharath.rest.webservices.restfulwebservices.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class UserController {

    @Autowired
    UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> returnAllUsers(){
        return  userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> returnUser(@PathVariable int id){
        User user=userDaoService.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id:" + id);
       EntityModel<User> entityModel=EntityModel.of(user);

        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).returnAllUsers());
        WebMvcLinkBuilder DelLink=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).DeleteUser(id));

        entityModel.add(DelLink.withRel("del-user"));

        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> AddUser(@Valid @RequestBody User user){
       User savedUser= userDaoService.SaveUser(user);
        URI url=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(url).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable int id) {
        boolean deleted = userDaoService.DeleteUser(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    @GetMapping("/users/{id}/posts")
    public List<Posts> returnPostsOfUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id:" + id);
        return user.getPostsList();
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> AddPost(@Valid @RequestBody Posts post,@PathVariable int id){
        Posts posts= userDaoService.AddPost(id,post);
        if(posts==null)
            throw new UserNotFoundException("id: "+ id);
        URI url=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(posts.getId()).toUri();
        return ResponseEntity.created(url).build();
    }
}

//It is important to send the right httpstatus response back to the frontend, so what we need to do is make sure that
// null is returned whenever the user with the id is not found in the database. then using the if condition, if null , then
//throw an exception an instance of a class which u create by extending the RuntimeException class and create a constructor
//accepting a string(which is the message you want to print) and calling the super() with the received string as parameter
// and add the annotation @ResponseStatus(code=HttpResponse.NOT_FOUND).


//If you want to customise the format of the response u return, then create a class with the fields as the components of the
//format u need, then add constructor, getters and setters just like creating a bean. and then extend it from the
//ResponseEntityExceptionHandler class which is an inbuilt class of spring to handle all exceptions. then override the method
//handleException and then customise it and return the configured ResponseEntity.(Dont forget to change the name of the method you
//override, the method name cant be the same  and add the annotation @ResponseStatus and inside it set it to handle all exceptions
//by putting Exception.class. And for the class add the ControllerAdvice annotation(used for exception handling, specialisation
//of component class.

