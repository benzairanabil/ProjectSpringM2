package org.cigma.ecom.dto;

import java.io.Serializable;



public class GalerieDto implements Serializable{
	
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int id;
	    private ArticleDto article;
	    private byte[] image;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public ArticleDto getArticle() {
			return article;
		}
		public void setArticle(ArticleDto article) {
			this.article = article;
		}
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
}
