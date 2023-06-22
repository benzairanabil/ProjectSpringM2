package org.cigma.ecom.service;

import org.cigma.ecom.dto.ArticleDto;
import org.cigma.ecom.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {
	
    ArticleDto insertArticle(ArticleDto a, String username);

    ArticleDto updateArticle(ArticleDto a, String username);
    

    void deleteArticle(Long id, String username);

    ArticleDto selectOne(Long id);

    List<ArticleDto> selectAll();

    List<ArticleDto> selectByUser(String username);

    Page<ArticleDto> getPage(Pageable p);

    List<ArticleDto> search(String search);

    Page<ArticleDto> search(String search, Pageable p);
}
