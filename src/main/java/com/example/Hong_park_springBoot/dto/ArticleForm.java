package com.example.Hong_park_springBoot.dto;

import com.example.Hong_park_springBoot.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content); // ID는 null로 설정하여 자동 생성
    }
}