package com.example.Hong_park_springBoot.api;

import com.example.Hong_park_springBoot.dto.ArticleForm;
import com.example.Hong_park_springBoot.entity.Article;
import com.example.Hong_park_springBoot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {//@RequestBody: json형식을 dto로 받아오는 어노테이션
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
        //dto를 엔티티로 변환
        Article article = form.toEntity();
        log.info("id: {},article: {},", id, article.toString());
        //id로 수정할 데이터 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청: id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //수정완료 및 응답
        target.patch(article);//데이터 일부만 수정해서 보내면 수정 안할 곳의 데이터도 같이 사라지기 때문에
        //기존데이터에 새 데이터를 붙혀주어야 한다.
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 없으면 4xx
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //정상 삭제 처리
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
