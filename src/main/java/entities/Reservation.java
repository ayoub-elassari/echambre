package entities;

public class Reservation {
	Long id;
	Long id_user;
	Long id_chambre;
	String nom_user;
	String prenom;
	String nom_chambre;
	
	public Reservation() {
		super();
	}

	public Reservation(Long id_user, Long id_chambre, String nom_user, String prenom, String nom_chambre) {
		super();
		this.id_user = id_user;
		this.id_chambre = id_chambre;
		this.nom_user = nom_user;
		this.prenom = prenom;
		this.nom_chambre = nom_chambre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_chambre() {
		return id_chambre;
	}

	public void setId_chambre(Long id_chambre) {
		this.id_chambre = id_chambre;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom_chambre() {
		return nom_chambre;
	}

	public void setNom_chambre(String nom_chambre) {
		this.nom_chambre = nom_chambre;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", id_user=" + id_user + ", id_chambre=" + id_chambre + ", nom_user=" + nom_user
				+ ", prenom=" + prenom + ", nom_chambre=" + nom_chambre + "]";
	}
	
	

}
