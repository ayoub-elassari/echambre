package entities;

public class ChambreReserved {
	private Long id;
	private Long id_user;
	private Long id_chambre;
	
	
	
	public ChambreReserved() {
		super();
	}
	public ChambreReserved(Long id_user, Long id_chambre) {
		super();
		this.id_user = id_user;
		this.id_chambre = id_chambre;
	}
	public ChambreReserved(Long id, Long id_user, Long id_chambre) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_chambre = id_chambre;
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
	@Override
	public String toString() {
		return "ChambreReserved [id=" + id + ", id_user=" + id_user + ", id_chambre=" + id_chambre + "]";
	}
	
	
	
	
}
