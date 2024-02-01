package com.example.joblisting.repository;

import java.util.Random;

import com.example.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class SearchRepoAction implements SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("trevor");
        MongoCollection<Document> collection = database.getCollection("jobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList
                       (new Document("$search",
                        new Document("index", "default")
                        .append("text",
                        new Document("query", text)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));


        return posts;
    }

    @Override
    public String RandomNumberGenerator() {

        Random random = new Random();
        StringBuilder randomCodeBuilder = new StringBuilder();

        // Generate 20 random digits and append them to the StringBuilder
        for (int i = 0; i < 20; i++) {
            int randomDigit = random.nextInt(10);
            randomCodeBuilder.append(randomDigit);
        }

        return randomCodeBuilder.toString();
    }


    }
