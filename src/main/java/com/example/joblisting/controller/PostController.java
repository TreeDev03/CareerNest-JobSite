package com.example.joblisting.controller;

import com.example.joblisting.repository.PostRepository;
import com.example.joblisting.model.Post;
import com.example.joblisting.repository.SearchRepoAction;
import com.example.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")

public class PostController {



    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;






    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPost() {

        return repo.findAll();

    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text) {


        return srepo.findByText(text);
    }

    @PostMapping("/post")
    @CrossOrigin
    public Post addPost(@RequestBody Post post) {
        SearchRepoAction s = new SearchRepoAction();
        String id= s.RandomNumberGenerator();
        post.setId(id);
        return repo.save(post);
    }



    @DeleteMapping("/{id}")
    @CrossOrigin
    public void deletePost(@PathVariable String id) {

         repo.deleteById(id);
    }


    }





