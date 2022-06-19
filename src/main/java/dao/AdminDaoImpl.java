package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Chambre;
import entities.Reservation;
import entities.Utilisateur;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public void AddBook(Chambre chambre, String categorie, String etage) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("INSERT INTO CHAMBRE (NOM, DESCRIPTION, PRIX) VALUES(?, ?, ?)");
			ps.setString(1, chambre.getNom());
			
			ps.setString(2, chambre.getDescription());
			
			ps.setInt(3, chambre.getprix());
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CHAMBRE");
			ResultSet rs2 = ps2.executeQuery();//contient max id CHAMBRE
			
			
			/*travaille sur catégorie*/
			PreparedStatement ps3_1 = connection.prepareStatement
					("SELECT * FROM CATEGORIE");
			ResultSet rs3_1 = ps3_1.executeQuery();
			boolean state = true;
			while(rs3_1.next()) {
				if(rs3_1.getString("GENRE").equals(categorie)) {
					state = false;
				}
			}
			
			if(state == true) {
				PreparedStatement ps3 = connection.prepareStatement
						("INSERT INTO CATEGORIE (GENRE) VALUES (?)");
				ps3.setString(1, categorie);
				ps3.executeUpdate();
			}
			
			PreparedStatement ps3_2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CATEGORIE");
			ResultSet rs3_2 = ps3_2.executeQuery();
			
			if(rs2.next() && rs3_2.next()) {
				System.out.println("max id livre" + rs2.getInt("MAX_ID"));
				PreparedStatement ps3_3 = connection.prepareStatement
						("INSERT INTO CATEGORIE_CHAMBRE (ID_CHAMBRE, ID_CATEGORIE) VALUES(?,?)");
				System.out.println("f 57 mzian");
				ps3_3.setInt(1, rs2.getInt("MAX_ID"));
				ps3_3.setInt(2, rs3_2.getInt("MAX_ID"));
				ps3_3.executeUpdate();
			}
			
			/*travaille sur l'auteur*/
			PreparedStatement ps4 = connection.prepareStatement
					("SELECT * FROM ETAGE");
			ResultSet rs4 = ps4.executeQuery();
			boolean stateAuteur = true;
			while(rs4.next()) {
				if(rs4.getString("nom").equals(etage)) {
					stateAuteur = false;
				}
			}
			
			if(stateAuteur == true) {
				PreparedStatement ps5 = connection.prepareStatement
						("INSERT INTO ETAGE (NOM) VALUES (?)");
				ps5.setString(1, etage);
				ps5.executeUpdate();
			}
			
			PreparedStatement ps6 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM ETAGE");
			ResultSet rs6 = ps6.executeQuery();
			
			
			
			PreparedStatement ps2_2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CHAMBRE");
			ResultSet rs2_2 = ps2_2.executeQuery();//contient max id livre
			
			
			if(rs2_2.next() && rs6.next()) {
				
				PreparedStatement ps7 = connection.prepareStatement
						("INSERT INTO ETAGE_CHAMBRE (ID_CHAMBRE, ID_ETAGE) values (?, ?)");
				System.out.println("hahowa 98");
				ps7.setInt(1, rs2_2.getInt("MAX_ID"));
				ps7.setInt(2, rs6.getInt("MAX_ID"));
				ps7.executeUpdate();
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Chambre> getBooks() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT * FROM CHAMBRE");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Chambre chambre = new Chambre();
				chambre.setId(rs.getLong("ID"));
				chambre.setNom(rs.getString("NOM"));
				
				chambre.setDescription(rs.getString("DESCRIPTION"));
				
				chambre.setprix(rs.getInt("PRIX"));
				chambres.add(chambre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chambres;
	}

	@Override
	public void deleteBook(Long id_chambre) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("DELETE FROM CHAMBRE WHERE ID = ?");
			ps.setLong(1, id_chambre);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Utilisateur> getUsers() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT * FROM USER");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(rs.getLong("ID"));
				utilisateur.setNom(rs.getString("NOM"));
				utilisateur.setPrenom(rs.getString("PRENOM"));
				utilisateur.setNumero(rs.getString("NUMERO"));
				utilisateur.setEmail(rs.getString("EMAIL"));
				utilisateur.setMot_de_passe(rs.getString("MOT_DE_PASSE"));
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public void deleteUser(Long id_user) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("DELETE FROM USER WHERE ID = ?");
			ps.setLong(1, id_user);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Reservation> getReservation() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT reservation.id, reservation.id_user, reservation.id_chambre, user.nom as nom_user, user.prenom ,chambre.nom as nom_chambre FROM reservation join user join chambre on reservation.id_user = user.id and reservation.id_chambre = chambre.id");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation();
				reservation.setId(rs.getLong("id"));
				reservation.setId_user(rs.getLong("ID_USER"));
				reservation.setId_chambre(rs.getLong("ID_CHAMBRE"));
				reservation.setNom_user(rs.getString("NOM_USER"));
				reservation.setPrenom(rs.getString("PRENOM"));
				reservation.setNom_chambre(rs.getString("NOM_CHAMBRE"));
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}

	@Override
	public void deleteReservation(Long id) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("DELETE FROM RESERVATION WHERE id = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void modifier(Chambre chambre, String categorie, String etage) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("UPDATE `chambre` set `nom`=?,`description`=?,`prix`=? WHERE id = ?");
			ps.setString(1, chambre.getNom());
			
			ps.setString(2, chambre.getDescription());
		
			ps.setInt(3, chambre.getprix());
			
			ps.setLong(4, chambre.getId());
			ps.executeUpdate();
			
			
			PreparedStatement ps2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CHAMBRE");
			ResultSet rs2 = ps2.executeQuery();//contient max id livre
			
			
			/*travaille sur catégorie*/
			PreparedStatement ps3_1 = connection.prepareStatement
					("SELECT * FROM CATEGORIE");
			ResultSet rs3_1 = ps3_1.executeQuery();
			boolean state = true;
			while(rs3_1.next()) {
				if(rs3_1.getString("GENRE").equals(categorie)) {
					state = false;
				}
			}
			
			if(state == true) {
				PreparedStatement ps3 = connection.prepareStatement
						("INSERT INTO CATEGORIE (GENRE) VALUES (?)");
				ps3.setString(1, categorie);
				ps3.executeUpdate();
			}
			
			PreparedStatement ps3_2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CATEGORIE");
			ResultSet rs3_2 = ps3_2.executeQuery();
			
			if(rs2.next() && rs3_2.next()) {
				System.out.println("max id chambre" + rs2.getInt("MAX_ID"));
				PreparedStatement ps3_3 = connection.prepareStatement
						("INSERT INTO CATEGORIE_CHAMBRE (ID_CHAMBRE, ID_CATEGORIE) VALUES(?,?)");
				System.out.println("f 57 mzian");
				ps3_3.setInt(1, rs2.getInt("MAX_ID"));
				ps3_3.setInt(2, rs3_2.getInt("MAX_ID"));
				ps3_3.executeUpdate();
			}
			
			/*travaille sur l'auteur*/
			PreparedStatement ps4 = connection.prepareStatement
					("SELECT * FROM ETAGE");
			ResultSet rs4 = ps4.executeQuery();
			boolean stateAuteur = true;
			while(rs4.next()) {
				if(rs4.getString("nom").equals(etage)) {
					stateAuteur = false;
				}
			}
			
			if(stateAuteur == true) {
				PreparedStatement ps5 = connection.prepareStatement
						("INSERT INTO ETAGE (NOM) VALUES (?)");
				ps5.setString(1, etage);
				ps5.executeUpdate();
			}
			
			PreparedStatement ps6 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM ETAGE");
			ResultSet rs6 = ps6.executeQuery();
			
			
			
			PreparedStatement ps2_2 = connection.prepareStatement
					("SELECT MAX(ID) AS MAX_ID FROM CHAMBRE");
			ResultSet rs2_2 = ps2_2.executeQuery();//contient max id livre
			
			
			if(rs2_2.next() && rs6.next()) {
				
				PreparedStatement ps7 = connection.prepareStatement
						("INSERT INTO ETAGE_CHAMBRE (ID_CHAMBRE, ID_ETAGE) values (?, ?)");
				System.out.println("hahowa 98");
				ps7.setInt(1, rs2_2.getInt("MAX_ID"));
				ps7.setInt(2, rs6.getInt("MAX_ID"));
				ps7.executeUpdate();
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
