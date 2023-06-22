package org.cigma.ecom.service;

import org.cigma.ecom.dto.ArticleDto;
import org.cigma.ecom.model.Article;
import org.cigma.ecom.repository.ArticleRepository;
import org.cigma.ecom.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImp implements IArticleService {
    @Autowired
    ArticleRepository repository;
    @Autowired
    UserRepository userRepo;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public ArticleDto insertArticle(ArticleDto a, String username) {
    	Article articelParam=modelMapper.map(a, Article.class);
        if (articelParam.getStock() > 0) {
        	articelParam.setVendeur(userRepo.findUserByUsername(username));
            //return repository.save(a);
            Article articelEntity = repository.save(articelParam);  		
    		ArticleDto ArticleDto = modelMapper.map(articelEntity, ArticleDto.class);
    		
    		return ArticleDto;
        }
        return null;
    }

    @Override
    public ArticleDto updateArticle(ArticleDto a, String username) {
        Article old = repository.findById(a.getId()).get();
        //old.getVendeur().getUsername() == username ==> false
        if (username.equals(old.getVendeur().getUsername())) {
            old.setTitre(a.getTitre());
            old.setDescription(a.getDescription());
            if (a.getStock() > 0)
                old.setStock(a.getStock());
            else old.setStock(1);
            old.setType(a.getType());
            //return repository.save(old);
            Article articelEntity = repository.save(old);  		
    		ArticleDto ArticleDto = modelMapper.map(articelEntity, ArticleDto.class);
    		
    		return ArticleDto;
        }
        return null;
    }

    @Override
    public void deleteArticle(Long id,String username) {
    	
        if (!repository.existsArticleOnPanier(id) && username.equals(repository.findById(id).get().getVendeur().getUsername()))
            repository.deleteById(id);
        else System.out.println("can't remove this article");
        
		

    }

    @Override
    public ArticleDto selectOne(Long id) {
    	
        
        Article articelEntity = repository.findArticleByid(id);
		
		ArticleDto ArticleDto = modelMapper.map(articelEntity, ArticleDto.class);
		
		return ArticleDto;
    }

    @Override
    public List<ArticleDto> selectAll() {
       
        
       List<Article> articlesEntity = repository.findAll();
       Type listType = new TypeToken<List<ArticleDto>>() {}.getType();
       List<ArticleDto> articlesDto = modelMapper.map(articlesEntity, listType);
       
       return articlesDto;
       
    }

    @Override
    public List<ArticleDto> selectByUser(String username) {
    	
        List<Article> articlesEntity = repository.findArticlesByVendeur_Username(username);
        Type listType = new TypeToken<List<ArticleDto>>() {}.getType();
        List<ArticleDto> articlesDto = modelMapper.map(articlesEntity, listType);
        
        return articlesDto;
    }

    @Override
    public Page<ArticleDto> getPage(Pageable p) {
    	
    	 Page<Article> articlesEntity = repository.findAll(p);
         Type listType = new TypeToken<Page<ArticleDto>>() {}.getType();
         Page<ArticleDto> articlesDto = modelMapper.map(articlesEntity, listType);
         
         return articlesDto;
    }

    @Override
    public List<ArticleDto> search(String search) {
        //return repository.findArticlesByTitreContains(search);
        //return repository.findByTitreContainsOrDescriptionContains(search, search);       
        List<Article> articlesEntity = repository.findByTitreContainsOrDescriptionContains(search, search);
        Type listType = new TypeToken<List<ArticleDto>>() {}.getType();
        List<ArticleDto> articlesDto = modelMapper.map(articlesEntity, listType);
        
        return articlesDto;
    }

    @Override
    public Page<ArticleDto> search(String search, Pageable p) {
        //return repository.findArticlesByTitreContains(search, p);
        //return repository.findByTitreContainsOrDescriptionContains(search, search, p);
    	Page<Article> articlesEntity = repository.findByTitreContainsOrDescriptionContains(search, search, p);
        Type listType = new TypeToken<Page<ArticleDto>>() {}.getType();
        Page<ArticleDto> articlesDto = modelMapper.map(articlesEntity, listType);
        
        return articlesDto;
    }





}
