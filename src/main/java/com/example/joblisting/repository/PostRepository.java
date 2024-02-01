package com.example.joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.joblisting.model.Post;

public interface PostRepository extends MongoRepository<Post,String> {

    public Post findByProfile(String desc);




}
