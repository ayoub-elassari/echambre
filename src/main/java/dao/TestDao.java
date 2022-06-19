package dao;

import java.util.ArrayList;
import java.util.List;

import entities.Chambre;
import entities.ChambreReserved;
import entities.Utilisateur;

public class TestDao {

	public static void main(String[] args) {
		/*UtilisateurDaoImpl dao = new UtilisateurDaoImpl();
		Utilisateur utilisateur = new Utilisateur("ayoub", "elassari", "06568265", "ayoub@gmail.com", "123123");
		Utilisateur utilisateur2 = new Utilisateur("ayoubb", "elassari", "06568265", "ayoub@gmail.com", "123123");
		dao.saveUser(utilisateur);
		dao.saveUser(utilisateur2);
		System.out.println("bien fait");
		System.out.println(utilisateur.toString());
		System.out.println(utilisateur2.toString());
		System.out.println("checher utilisteur");
		Utilisateur utilisateur3;
		utilisateur3 = dao.getUser("ayoub@gmail.com", "123123");
		System.out.println(utilisateur3);*/
		
		
		
		/* test des chambres*/
		/*List<Chambre> chambres;
		ChambreDaoImpl dao = new ChambreDaoImpl();
		chambres = dao.getBooks("%" + "rich" + "%");
		for(Chambre livre: chambres) {
			System.out.println(livre);
		}*/
		
		/* test livre d'un utilisateur*/
		/*ChambreDaoImpl dao = new ChambreDaoImpl();
		List<ChambreReserved> livresReserved;
		livresReserved = dao.getBooksReserved(1L);
		for(ChambreReserved livreReserved: livresReserved) {
			System.out.println(livreReserved);
			System.out.println("sala");
		}*/
		
		/*reserver livre test*/
		ChambreDaoImpl dao = new ChambreDaoImpl();
		dao.reserverLivre(12L, 2L);

	}

}
