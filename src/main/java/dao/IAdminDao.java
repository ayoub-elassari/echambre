package dao;

import java.util.List;

import entities.Chambre;
import entities.Reservation;
import entities.Utilisateur;



public interface IAdminDao {
	public void AddBook(Chambre chambre, String categorie, String etage );
	public List<Chambre> getBooks();
	public void deleteBook(Long id_livre);
	public List<Utilisateur> getUsers();
	public void deleteUser(Long id_user);
	public List<Reservation> getReservation();
	public void deleteReservation(Long id);
	public void modifier(Chambre chambre, String categorie, String etage);
	
}
