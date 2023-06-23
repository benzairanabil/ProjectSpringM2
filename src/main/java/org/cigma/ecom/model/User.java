package org.cigma.ecom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_USER")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique=true)
    private String email;
    private String role = "USER";
    private String nom;
    private String tele;
    private String prenom;
    private String ville;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Panier> paniers;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Article> listArticles;

    public void setUsername(String username) {
            this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String newPassword, String oldPassword) {
        if (this.password == oldPassword) {
            this.password = newPassword;
        }
    }

    public void hidePassword() {
        this.password = "xxxxxx";
    }

    public boolean signin(String username, String password) {
        if (this.username == username || this.email == username) {
            return this.password == password;
        } else {
            return false;
        }
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public void setListArticles(List<Article> listArticles) {
        this.listArticles = listArticles;
    }

    public String getNom() {
        return nom;
    }

    public String getTele() {
        return tele;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

}
