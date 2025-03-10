package com.example.Hong_park_springBoot;

import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.entity.Coffee;
import com.example.Hong_park_springBoot.entity.Comment;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import com.example.Hong_park_springBoot.repository.CoffeeRepository;
import com.example.Hong_park_springBoot.repository.CommentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer { // 클래스 이름 변경
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    public void init() {
        Article article1 = articleRepository.save(new Article(null, "가가가가", "1111"));
        Article article2 = articleRepository.save(new Article(null, "나나나나", "2222"));
        Article article3 = articleRepository.save(new Article(null, "다다다다", "3333"));
        Article article4 = articleRepository.save(new Article(null, "당신의 인생 영화는?", "댓글 고"));
        Article article5 = articleRepository.save(new Article(null, "당신의 소울 푸드는?", "댓글 고고"));
        Article article6 = articleRepository.save(new Article(null, "당신의 취미는", "댓글 고고고"));
        coffeeRepository.save(new Coffee(null, "아메리카노", "4500"));
        coffeeRepository.save(new Coffee(null, "라떼", "5000"));
        coffeeRepository.save(new Coffee(null, "카페 모카", "5500"));
        commentRepository.save(new Comment(null,article4,"Park","굿 윌 헌팅"));
        commentRepository.save(new Comment(null,article4,"Kim","아이 엠 샘"));
        commentRepository.save(new Comment(null,article4,"Choi","쇼생크 탈출"));
        commentRepository.save(new Comment(null,article5,"Park","치킨"));
        commentRepository.save(new Comment(null,article5,"Kim","샤브샤브"));
        commentRepository.save(new Comment(null,article5,"Choi","초밥"));
        commentRepository.save(new Comment(null,article6,"Park","조깅"));
        commentRepository.save(new Comment(null,article6,"Kim","유튜브 시청"));
        commentRepository.save(new Comment(null,article6,"Choi","독서"));

    }
}