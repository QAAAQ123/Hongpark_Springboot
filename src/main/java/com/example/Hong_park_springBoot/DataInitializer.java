package com.example.Hong_park_springBoot;

import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.entity.Coffee;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import com.example.Hong_park_springBoot.repository.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer { // 클래스 이름 변경
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    @PostConstruct
    public void init() {
        articleRepository.save(new Article(null, "가가가가", "1111"));
        articleRepository.save(new Article(null, "나나나나", "2222"));
        articleRepository.save(new Article(null, "다다다다", "3333"));
        coffeeRepository.save(new Coffee(null, "아메리카노", "4500"));
        coffeeRepository.save(new Coffee(null, "라떼", "5000"));
        coffeeRepository.save(new Coffee(null, "카페 모카", "5500"));

    }
}