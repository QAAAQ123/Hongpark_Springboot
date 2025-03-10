package com.example.Hong_park_springBoot.service;

import com.example.Hong_park_springBoot.dto.ArticleForm;
import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm form) {
        //dto를 엔티티로 변환
        Article article = form.toEntity();
        log.info("id: {},article: {},", id, article.toString());
        //id로 수정할 데이터 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청: id: {}, article: {}", id, article.toString());
            return null;
        }
        //수정완료 및 응답
        target.patch(article);//데이터 일부만 수정해서 보내면 수정 안할 곳의 데이터도 같이 사라지기 때문에
        //기존데이터에 새 데이터를 붙혀주어야 한다.
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 없으면 4xx
        if(target == null){
            return null;
        }
        //정상 삭제 처리
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto -> entity
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //엔티티를 DB에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));
        //결과 반환
        return articleList;
    }

}
