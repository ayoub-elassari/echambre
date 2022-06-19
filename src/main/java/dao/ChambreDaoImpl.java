package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Chambre;
import entities.ChambreReserved;

public class ChambreDaoImpl implements IChambreDao {

	@Override
	public List<Chambre> getBooks(String motCle) {
		List<Chambre> chambres = new ArrayList<Chambre>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT * FROM CHAMBRE WHERE NOM LIKE ?");
			ps.setString(1, motCle);
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
	public List<ChambreReserved> getBooksReserved(Long id_user) {
		List<ChambreReserved> chambreReserved = new ArrayList<ChambreReserved>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT * FROM RESERVATION WHERE id_user LIKE ?");
			ps.setLong(1, id_user);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ChambreReserved livreReserved = new ChambreReserved();
				livreReserved.setId(rs.getLong("ID"));
				livreReserved.setId_user(rs.getLong("ID_USER"));
				livreReserved.setId_chambre(rs.getLong("ID_Chambre"));
				chambreReserved.add(livreReserved);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chambreReserved;
	}

	@Override
	public void reserverLivre(Long id_user, Long id_chambre) {
		Connection connection =  SingletonConnection.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement
					("SELECT * FROM RESERVATION");
			boolean statu = true;
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Long table_user_id = rs.getLong("ID_USER");
				Long tabble_chambre_id = rs.getLong("ID_CHAMBRE");
				if(table_user_id == id_user && tabble_chambre_id == id_chambre) {
					statu = false;
				}
			}
			if(statu == true) {
				PreparedStatement ps2 = connection.prepareStatement
						("INSERT INTO RESERVATION (ID_USER, ID_CHAMBRE) VALUES (?, ?)");
				ps2.setLong(1, id_user);
				ps2.setLong(2, id_chambre);
				ps2.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Chambre> listReserved(Long id_user) {
		List<Chambre> chambres = new ArrayList<Chambre>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT chambre.id, chambre.nom, description, prix "
							+ "FROM reservation JOIN chambre JOIN user "
							+ "ON reservation.id_user=user.id AND reservation.id_chambre=chambre.id WHERE user.id = ?");
			ps.setLong(1, id_user);
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
	public void deleteReservation(Long id_user, Long id_chambre) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("DELETE FROM RESERVATION WHERE id_user = ? and id_chambre = ?");
			ps.setLong(1, id_user);
			ps.setLong(2, id_chambre);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Chambre> getRecommendations() {
		List<Chambre> chambres = new ArrayList<Chambre>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("SELECT id_chambre, COUNT(id_chambre) as nbrReservation from reservation GROUP BY id_chambre ASC LIMIT 3;");
			ResultSet rs = ps.executeQuery();
			Long id1=0L, id2=0L, id3=0L;
			if(rs.next()) {
				id1 = rs.getLong("ID_CHAMBRE");
			}
			if(rs.next()) {
				id2 = rs.getLong("ID_CHAMBRE");
			}
			if(rs.next()) {
				id3 = rs.getLong("ID_CHAMBRE");
			}
			
			PreparedStatement ps2 = connection.prepareStatement
					("SELECT * FROM livre WHERE id = ? or id = ? or id = ?");
			ps2.setLong(1, id1);
			ps2.setLong(2, id2);
			ps2.setLong(3, id3);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				Chambre chambre = new Chambre();
				chambre.setId(rs2.getLong("ID"));
				chambre.setNom(rs2.getString("NOM"));
				
				chambre.setDescription(rs2.getString("DESCRIPTION"));
				
				chambre.setprix(rs2.getInt("PAGES"));
				
				chambres.add(chambre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(chambres);
		return chambres;
	}

}
