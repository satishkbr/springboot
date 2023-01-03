package com.tsk.springboot.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.springboot.restfulwebservice.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
