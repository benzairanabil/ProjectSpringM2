package org.cigma.ecom.dto;

import java.io.Serializable;
import java.util.Date;


import org.cigma.ecom.model.Article;
import org.cigma.ecom.model.User;


public class PanierDto implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	    private int quantite;
	    private Date date;
	    private ArticleDto article;
	    
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public ArticleDto getArticle() {
			return article;
		}
		public void setArticle(ArticleDto article) {
			this.article = article;
		}
		
}
