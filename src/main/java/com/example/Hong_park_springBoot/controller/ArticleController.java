package com.example.Hong_park_springBoot.controller;

import com.example.Hong_park_springBoot.dto.ArticleForm;
import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired //DI
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String createArticle(ArticleForm form){ //form: dto,new.mustache의 name=""과 연결되어있음,
        // new에서 submit버튼 누르면 create로 페이지 이동하면서 form데이터를 전송받아 DTO객체에 담겨진다
        System.out.println(form.toString());
        //DB에 저장
        //1. DTO(form)을 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());
        //2. 엔티티를 리파지터리로 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
