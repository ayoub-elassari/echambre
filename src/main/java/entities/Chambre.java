package entities;

import java.io.Serializable;

public class Chambre implements Serializable {

	private Long id;
	private String nom;
	
	private String description;
	
	private int prix;
	
	
	public Chambre() {
		super();
	}

	public Chambre(String nom, String description,  int prix) {
		super();
		this.nom = nom;
		
		this.description = description;
		
		this.prix = prix;
	
	}

	public Chambre(Long id, String nom, String description,
			int prix) {
		super();
		this.id = id;
		this.nom = nom;
		
		this.description = description;
		
		this.prix = prix;
		
	}

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public int getprix() {
		return prix;
	}

	public void setprix(int prix) {
		this.prix = prix;
	}

	
	@Override
	public String toString() {
		return "Chambre [id=" + id + ", nom=" + nom + ",  description=" + description
				 + ", prix=" + prix 
				;
	}
	
	
}
