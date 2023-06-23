package org.cigma.ecom.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.cigma.ecom.model.Article;
import org.cigma.ecom.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanierDto implements Serializable{
	   /**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private int id;
	    private int quantite;
	    private Date date;
	    private ArticleDto article;
	    
	    		
}
