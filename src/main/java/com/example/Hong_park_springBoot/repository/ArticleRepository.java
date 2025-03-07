package com.example.Hong_park_springBoot.repository;

import com.example.Hong_park_springBoot.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
