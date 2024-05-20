package com.bharath.rest.webservices.restfulwebservices.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   private String Description;
   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore
   private User user;

    public Posts() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Posts(int id, String description) {
        this.id = id;
        Description = description;
    }
}
