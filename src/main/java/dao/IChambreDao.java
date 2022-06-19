package dao;

import java.util.List;

import entities.Chambre;
import entities.ChambreReserved;

public interface IChambreDao {
	public List<Chambre> getBooks (String MotCle);
	public List<ChambreReserved> getBooksReserved (Long id_user);
	public void reserverLivre(Long id_user, Long id_chambre);
	public List<Chambre> listReserved(Long id_user);
	public void deleteReservation(Long id_user, Long id_chambre);
	public List<Chambre> getRecommendations();
}
