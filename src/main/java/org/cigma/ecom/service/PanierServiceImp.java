package org.cigma.ecom.service;

import org.cigma.ecom.dto.ArticleDto;
import org.cigma.ecom.dto.PanierDto;
import org.cigma.ecom.dto.UserDto;
import org.cigma.ecom.dto.UserLoginResponse;
import org.cigma.ecom.model.Article;
import org.cigma.ecom.model.Panier;
import org.cigma.ecom.repository.PanierRepository;
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
public class PanierServiceImp implements IPanierService {
    @Autowired
    PanierRepository repository;
    @Autowired
    UserRepository user;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public PanierDto insertPanier(PanierDto p, String username) {
        if (p.getQuantite() > 0) {
            List<Panier> myPaniers = repository.findPanierByProprietaire_Username(username);
            for (int i = 0; i < myPaniers.toArray().length; i++) {
                Panier selected = myPaniers.get(i);
                if (selected.getArticle().getId() == p.getArticle().getId()) {
                    selected.setQuantite(selected.getQuantite() + p.getQuantite());
                    p = modelMapper.map(p, PanierDto.class);
                }
            }
            //return repository.save(p);
            Panier panierEntity = repository.save(modelMapper.map(p, Panier.class));  		
    		PanierDto panierDto = modelMapper.map(panierEntity, PanierDto.class);
    		return panierDto;
        }
        return null;
    }

    @Override
    public PanierDto updatePanier(PanierDto p) {
            Panier old = repository.findById(p.getId()).get();
            if (p.getQuantite() > 0)
                old.setQuantite(p.getQuantite());
            else old.setQuantite(1);
            old.setDate(p.getDate());
            old.setArticle(modelMapper.map(p.getArticle(), Article.class));
            //return repository.save(old);
            Panier panierEntity = repository.save(old);  		
    		PanierDto panierDto = modelMapper.map(panierEntity, PanierDto.class);
    		return panierDto;

    }

    @Override
    public void deletePanier(int id, String username) {
        Panier p = repository.findById(id).get();
        System.out.println("Delete " + id);
        if (username.equals(p.getProprietaire().getUsername())){

            repository.deleteById(id);
        }
    }

    @Override
    public PanierDto selectOne(int id, String username) {
        Panier p = repository.findById(id).get();
        if (username.equals(p.getProprietaire().getUsername())) {
            p = repository.findById(id).get();
            //p.getProprietaire().hidePassword();
            return modelMapper.map(p, PanierDto.class);
        }
        return null;
    }

    @Override
    public List<PanierDto> selectAll(String username) {
        //return repository.findPanierByProprietaire_Username(username);
        
        Type listType = new TypeToken<List<PanierDto>>() {}.getType();
        return  modelMapper.map(repository.findPanierByProprietaire_Username(username), listType);
    }

    @Override
    public Page<PanierDto> getPage(Pageable p, String username) {
        //return repository.findPanierByProprietaire_Username(p,username);
    	 Type listType = new TypeToken<Page<PanierDto>>() {}.getType();
         return modelMapper.map(repository.findPanierByProprietaire_Username(p,username), listType);
    }
}
