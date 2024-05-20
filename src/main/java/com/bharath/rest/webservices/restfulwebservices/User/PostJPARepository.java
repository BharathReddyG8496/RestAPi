package com.bharath.rest.webservices.restfulwebservices.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJPARepository extends JpaRepository<Posts,Integer> {
}
