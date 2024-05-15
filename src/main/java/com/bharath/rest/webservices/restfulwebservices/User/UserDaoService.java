package com.bharath.rest.webservices.restfulwebservices.User;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class UserDaoService {

    private static List<User> users=new ArrayList<>();
    private static int count=0;
    static {
        users.add(new User(++count,"Harish", LocalDate.now().minusYears(21)));
        users.add(new User(++count,"Vineeth", LocalDate.now().minusYears(23).minusMonths(3).minusDays(20)));
        users.add(new User(++count,"Akanksh", LocalDate.now().minusYears(24).minusMonths(10)));
    }


    public List<User> findAll(){
        return users;
    }

    public User SaveUser(User e){
        e.setId(++count);
        users.add(e);
        return e;
    }

    public User findOne(int id){
        Predicate<? super User> predicate=user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void DeleteUser(int id){
        Predicate<? super User> predicate=user -> user.getId().equals(id);
       users.remove(predicate);

    }

}
