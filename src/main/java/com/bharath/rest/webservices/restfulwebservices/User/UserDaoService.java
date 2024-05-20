package com.bharath.rest.webservices.restfulwebservices.User;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
@Transactional
public class UserDaoService {

    @Autowired
     UserJPARepository userJPARepository;

     @Autowired
    PostJPARepository postJPARepository;


    public List<User> findAll(){
        return userJPARepository.findAll();
    }

    public User SaveUser(User e){
       return userJPARepository.save(e);
    }

    public  User findOne(int id){
     Optional<User> user= userJPARepository.findById(id);
     if (user==null){
         return null;
     }
     else return user.get();
    }

    public boolean DeleteUser(int id){
      Optional<User> user=userJPARepository.findById(id);
      if (user==null)
          return false;
      else{
          userJPARepository.delete(user.get());
          return true;
      }

    }
    public Posts AddPost(int id,Posts posts){
     Optional<User> user=userJPARepository.findById(id);
     if(user!=null){
         posts.setUser(user.get());
         return postJPARepository.save(posts);
     }
     else {
         return null;
     }
    }


}
