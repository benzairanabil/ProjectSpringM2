package org.cigma.ecom.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GalerieDto implements Serializable{
	
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int id;
	    private ArticleDto article;
	    private byte[] image;
	    

}
