package com.tsk.springboot.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsk.springboot.restfulwebservice.user.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {

}
