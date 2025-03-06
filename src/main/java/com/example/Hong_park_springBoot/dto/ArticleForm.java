package com.example.Hong_park_springBoot.dto;

import com.example.Hong_park_springBoot.entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ArticleForm(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public Article toEntity() {
        return new Article(null,title,content);
    }
}
