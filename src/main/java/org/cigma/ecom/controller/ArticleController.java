package org.cigma.ecom.controller;

import org.cigma.ecom.dto.ArticleDto;
import org.cigma.ecom.exceptions.ArticleException;
import org.cigma.ecom.exceptions.UserException;
import org.cigma.ecom.service.IArticleService;
import org.cigma.ecom.util.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/articles")
public class ArticleController {
    @Autowired
    IArticleService service;
    @Autowired
    CheckUser checkUser;

    @GetMapping(params = {"pageNumber", "pageSize"})
    public Page<ArticleDto> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
    	if(pageNumber==0)pageNumber=1;
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.getPage(pageParams);
    }

    @GetMapping(path = "/search", params = {"q", "pageNumber", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ArticleDto> search(@RequestParam("q") String search, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.search(search,pageParams);
    }

    @GetMapping(path = "/search", params = { "q" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> search(@RequestParam("q") String search){
        return service.search(search);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto getOne(@PathVariable("id") Long id) {
        return service.selectOne(id);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> getAll() {
        return service.selectAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> getMy(@RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.selectByUser(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto add(@RequestBody ArticleDto a, @RequestHeader("Authorization") String auth) {
    	System.out.println("tfoou auth"+auth);
    	String username = checkUser.getUsername(auth);
        System.out.println("tfoou"+username);
        if(a.getStock()<=0) throw new ArticleException("can't add article with stock <= 0");
        return service.insertArticle(a, username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto update(@RequestBody ArticleDto a, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.updateArticle(a, username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        service.deleteArticle(id, username);
    }
}
