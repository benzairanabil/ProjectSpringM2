package org.cigma.ecom.dto;


import java.io.Serializable;
import java.util.List;



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
	    
	    public List<String> getListImages() {
			return listImages;
		}
		public void setListImages(List<String> listImages) {
			this.listImages = listImages;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getPrix() {
			return prix;
		}
		public void setPrix(int prix) {
			this.prix = prix;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public UserLoginResponse getVendeur() {
			return vendeur;
		}
		public void setVendeur(UserLoginResponse vendeur) {
			this.vendeur = vendeur;
		}
		
}
