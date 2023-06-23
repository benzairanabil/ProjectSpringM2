package org.cigma.ecom.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticleDto implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
	    private String titre;
	    private String description;
	    private int prix = 0;
	    private String type;
	    private int stock = 0;
	    private String thumbnail;
	    private UserLoginResponse vendeur;
	    private List<String> listImages;
	    
		
}
