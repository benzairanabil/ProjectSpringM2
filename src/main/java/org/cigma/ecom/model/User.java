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
 

}
