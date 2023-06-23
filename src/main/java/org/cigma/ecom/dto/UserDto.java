package org.cigma.ecom.dto;

import java.io.Serializable;
import java.util.List;


import org.cigma.ecom.model.Article;
import org.cigma.ecom.model.Panier;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDto implements Serializable{
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String username;
    private String password;
    private String email;
    private String role = "USER";
    private String nom;
    private String tele;
    private String prenom;
    private String ville;
    private List<PanierDto> paniers;
    private List<ArticleDto> listArticles;
    
	
    

}
