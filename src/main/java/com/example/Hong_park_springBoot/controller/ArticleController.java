package com.example.Hong_park_springBoot.controller;

import com.example.Hong_park_springBoot.dto.ArticleForm;
import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired //DI
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ //form: dto,new.mustache의 name=""과 연결되어있음,
        // new에서 submit버튼 누르면 create로 페이지 이동하면서 form데이터를 전송받아 DTO객체에 담겨진다
        log.info(form.toString());
        //DB에 저장
        //1. DTO(form)을 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //2. 엔티티를 리파지터리로 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();

    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1.리파지터리 이용해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2.모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //3.뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //뷰 페이지 리턴
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update()
}
