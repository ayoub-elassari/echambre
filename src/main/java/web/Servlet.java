package web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.websocket.Session;

import dao.AdminDaoImpl;
import dao.IAdminDao;
import dao.IChambreDao;
import dao.IUtilisateurDao;
import dao.ChambreDaoImpl;
import dao.UtilisateurDaoImpl;
import entities.Chambre;
import entities.ChambreReserved;
import entities.Reservation;
import entities.Utilisateur;



@WebServlet(name = "servlet",urlPatterns = {"*.do"})
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUtilisateurDao metierUtilisteurDao;
	HttpSession session;
	List<Chambre> chambres;
	List<ChambreReserved> chambreReserveds;
	IChambreDao metierLivre;
	IAdminDao metierAdmin;
 
    public Servlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	metierUtilisteurDao = new UtilisateurDaoImpl();
    	metierLivre = new ChambreDaoImpl();
    	metierAdmin = new AdminDaoImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/visiteur.do")) {
			String motCle = request.getParameter("motCle");
			if(motCle == null) {
				chambres = metierLivre.getBooks("%%");
				request.setAttribute("chambres", chambres);
				request.getRequestDispatcher("visiteur.jsp").forward(request, response);
			}else {
				motCle = "%" + motCle + "%";
				/*System.out.println(motCle);*/
				chambres = metierLivre.getBooks(motCle);
				request.setAttribute("chambres", chambres);
				request.getRequestDispatcher("visiteur.jsp").forward(request, response);
			}
		}else if(path.equals("/seConnecter.do")) {
			request.getRequestDispatcher("connection.jsp").forward(request, response);
		}else if (path.equals("/sInscrire.do")) {
			request.getRequestDispatcher("inscription.jsp").forward(request, response);
		}else if(path.equals("/seDeconnecter.do")) {
			if(!(session == null)){
				session.invalidate();
			}
			response.sendRedirect("/e-chambre1/visiteur.do");
//			request.getRequestDispatcher("/visiteur.do").forward(request, response);
		} else if(path.equals("/utilisateur-chambres.do")) {
			
			
			String motCle = request.getParameter("motCle");
			if(motCle == null) {
				chambres = metierLivre.getBooks("%%");
				
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				chambreReserveds = metierLivre.getBooksReserved(utilisateur.getId());
				
				System.out.println(utilisateur.getId());
				for(ChambreReserved livreRserved: chambreReserveds) {
					System.out.println(livreRserved);
				}
				//System.out.println("maktb walo");
				request.setAttribute("chambreReserveds", chambreReserveds);
				request.setAttribute("chambres", chambres);
				request.getRequestDispatcher("utilisateur-chambres.jsp").forward(request, response);
			}else {
				motCle = "%" + motCle + "%";   
				chambres = metierLivre.getBooks(motCle);
				request.setAttribute("chambres", chambres);
				request.getRequestDispatcher("utilisateur-chambres.jsp").forward(request, response);
			}
			
			
//			response.sendRedirect("/e-chambre1/utilisateur-chambres.do");
			//request.getRequestDispatcher("utilisateur-chambres.jsp").forward(request, response);
		}else if(path.equals("/reserver.do")) {
			String string_user = request.getParameter("id_user");
			String string_livre = request.getParameter("id_chambre");
			Long id_user = (long) Integer.parseInt(string_user);
			Long id_livre = (long) Integer.parseInt(string_livre);
			System.out.println(id_user);
			System.out.println(id_livre);
			metierLivre.reserverLivre(id_user, id_livre);
			response.sendRedirect("/e-chambre1/utilisateur-chambres.do");
			
		}else if(path.equals("/livresReserves.do")) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			List<Chambre> chambres = metierLivre.listReserved(utilisateur.getId());
			for(Chambre chambre: chambres) {
				System.out.println(chambre);
				System.out.println(chambre.getId());
			}/*had hna mzian*/
			request.setAttribute("chambres", chambres);/*aji hna*/
			request.getRequestDispatcher("utilisateur-livresReserves.jsp").forward(request, response);
		}else if(path.equals("/deleteReservation.do")) {
			String string_user = request.getParameter("id_user");
			Long id_user = (long) Integer.parseInt(string_user);
			String string_livre = request.getParameter("id_chambre");
			Long id_livre = (long) Integer.parseInt(string_livre);
			metierLivre.deleteReservation(id_user, id_livre);
			response.sendRedirect("/e-chambre1/livresReserves.do");
		}else if(path.equals("/proposerLivre.do")) {
			request.getRequestDispatcher("utilisateur-proposer.jsp").forward(request, response);
		}else if(path.equals("/adminAjouterLivre.do")) {
			request.getRequestDispatcher("admin-ajouter.jsp").forward(request, response);
		}else if(path.equals("/adminAjouterInfoLivre.do")) {
			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			
			int prix = Integer.parseInt((String) request.getParameter("prix"));
			
			String categorie = request.getParameter("categorie");
			System.out.println("categorie:" + categorie);
			String etage = request.getParameter("etage");
			Chambre chambre = new Chambre();
			chambre.setNom(titre);
			chambre.setDescription(description);
			
			chambre.setprix(prix);
			
			metierAdmin.AddBook(chambre, categorie, etage);
			System.out.println("ici");//ha fin kayn lmochkil
			response.sendRedirect("/e-chambre1/adminAjouterLivre.do");
			
		}else if(path.equals("/admin-supprimer-livre.do")) {
			List<Chambre> chambres = metierAdmin.getBooks();
			System.out.println(chambres);
			request.setAttribute("chambres", chambres);
			request.getRequestDispatcher("admin-supprimer-livre.jsp").forward(request, response);
		}else if (path.equals("/supprimerLivre.do")) {
			String id_livre_string = request.getParameter("id_chambre");
			Long id_livre = (long) Integer.parseInt(id_livre_string);
			metierAdmin.deleteBook(id_livre);
			response.sendRedirect("/e-chambre1/admin-supprimer-livre.do");
		}else if(path.equals("/admin-supprimer-utilisateur.do")) {
			List<Utilisateur> utilisateurs;
			utilisateurs = metierAdmin.getUsers();
			request.setAttribute("utilisateurs", utilisateurs);
			request.getRequestDispatcher("admin-supprimer-utilisateur.jsp").forward(request, response);
		}else if(path.equals("/supprimerUtilisateur.do")) {
			String id_user_string = request.getParameter("id_user");
		    Long id_user = (long) Integer.parseInt(id_user_string);
			metierAdmin.deleteUser(id_user);
//			System.out.println(id_user_string);
//			System.out.println(id_user);
			response.sendRedirect("admin-supprimer-utilisateur.do");
		}else if(path.equals("/admin-supprimer-reservation.do")) {
			List<Reservation> reservations = metierAdmin.getReservation();
			request.setAttribute("reservations", reservations);
			System.out.println(reservations);
			request.getRequestDispatcher("admin-supprimer-reservation.jsp").forward(request, response);
		}else if(path.equals("/supprimerReservation.do")) {
			String id_string = request.getParameter("id");
			Long id = (long) Integer.parseInt(id_string);
			metierAdmin.deleteReservation(id);
			response.sendRedirect("/e-chambre1/admin-supprimer-reservation.do");
		}else if(path.equals("/utilisateur-recommandations.do")){
			List<Chambre> chambres = metierLivre.getRecommendations();
			System.out.println("195" + chambres);
			request.setAttribute("chambres", chambres);
			request.getRequestDispatcher("utilisateur-recommandations.jsp").forward(request, response);
		}else if(path.equals("/modifier-livre.do")) {
			request.getRequestDispatcher("admin-modifier.jsp").forward(request, response);
		}else if(path.equals("/adminmodifierInfoLivre.do"))	{
			String id_livre_string = request.getParameter("id_chambre");
			Long id_livre = (long) Integer.parseInt(id_livre_string);
			System.out.println(id_livre);
			
			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			
			int prix = Integer.parseInt((String) request.getParameter("prix"));
		
			String categorie = request.getParameter("categorie");
			System.out.println("categorie:" + categorie);
			String etage = request.getParameter("etage");
			Chambre chambre = new Chambre();
			chambre.setNom(titre);
			chambre.setDescription(description);
			
			chambre.setprix(prix);
			
			chambre.setId(id_livre);
			metierAdmin.modifier(chambre, categorie, etage);
			System.out.println("ici");//ha fin kayn lmochkil
			response.sendRedirect("/e-chambre1/admin-supprimer-livre.do");
			
		}
		
		
		
		
		}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		doGet(request, response);
		if (path.equals("/ajouterUtilisateur.do")) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setEmail(request.getParameter("email"));
			utilisateur.setNumero(request.getParameter("numero"));
			utilisateur.setMot_de_passe(request.getParameter("mot_de_passe"));
			utilisateur = metierUtilisteurDao.saveUser(utilisateur);
			System.out.println(utilisateur);
			
			session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect("/e-chambre1/utilisateur-chambres.do");
//			request.getRequestDispatcher("utilisateur-chambres.jsp").forward(request, response);
		} else if(path.equals("/verifier.do")) {
			String email = request.getParameter("email");
			String mot_de_passe = request.getParameter("mot_de_passe");
			Utilisateur utilisateur = metierUtilisteurDao.getUser(email, mot_de_passe);
			Long id = utilisateur.getId();
			if(id == null) {
				response.sendRedirect("/e-chambre1/seConnecter.do");
			}else {
				session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect("/e-chambre1/utilisateur-chambres.do");
			}
		}else if(path.equals("/ajouterOpinion.do")) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			Long id_user = utilisateur.getId();
			String titre = (String) request.getParameter("titre");
			String opinion = (String) request.getParameter("opinion");
			metierUtilisteurDao.addUserOpinion(id_user, titre, opinion);
			response.sendRedirect("/e-chambre1/proposerLivre.do");
		}
	}

}
