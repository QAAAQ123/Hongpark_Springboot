package com.example.Hong_park_springBoot.repository;

import com.example.Hong_park_springBoot.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {

}
