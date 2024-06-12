package com.ttc.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttc.spring.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}
