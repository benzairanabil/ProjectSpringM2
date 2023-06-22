package org.cigma.ecom.dto;

import java.io.Serializable;
import java.util.List;

public class UserLoginResponse implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String username;

    private String email;
    private String role = "USER";
    private String nom;
    private String tele;
    private String prenom;
    private String ville;
    private List<PanierDto> paniers;
    private List<ArticleDto> listArticles;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<PanierDto> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<PanierDto> paniers) {
		this.paniers = paniers;
	}

	public List<ArticleDto> getListArticles() {
		return listArticles;
	}

	public void setListArticles(List<ArticleDto> listArticles) {
		this.listArticles = listArticles;
	}

	

}
