package com.example.joblisting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.joblisting.model.Post;
public interface PostRepository extends MongoRepository<Post,String> {


}
