<%@page import="entities.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>utilisateur</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-info">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">E-chambre</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/e-chambre1/utilisateur-chambres.do">Les chambres</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="/e-chambre1/livresReserves.do">Les chambres réservés</a>
        </li>
        
      </ul>
      <form class="d-flex">
        <a href="/e-chambre1/seDeconnecter.do" class="btn btn-outline-success" type="submit">se déconnecter</a>
      </form>
    </div>
  </div>
</nav>


<!-- search bar -->
	<!-- <div class="navbar navbar-light bg-wight">
  		<div class="container-fluid justify-content-center">
		    <form class="d-flex">
		      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-primary" type="submit">Search</button>
		    </form>
  		</div>
	</div> -->
	
	
	<!-- liste des livres -->
	<br>
	

<c:forEach items="${chambres }" var="chambre">
<div class="accordion" id="accordionExample">	


  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target= aria-expanded="true" aria-controls="collapseOne">
        <h3>${chambre.nom }</h3>
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <p><strong>description</strong></p>
        <p >${chambre.description }</p>
		
		<p><strong>prix totale</strong> : ${chambre.prix }</p>
		<!-- <p><strong>Catégorie</strong> : littérature</p> -->
		
		<%Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur"); 
		%>
		<a href="/e-chambre1/deleteReservation.do?id_user=${utilisateur.id }&id_chambre=${chambre.id }" type="button" class="btn btn-outline-danger">annuler la réservation de cette chambre</a>
      </div>
    </div>

</div> 
</c:forEach>


	
	

<!-- <div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        <h3>The Hating Game</h3>
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <p><strong>description</strong></p>
        <p >Hutton and Joshua Templeman hate each other. Not dislike. Not
		begrudgingly tolerate. Hate. And they have no problem displaying their
		feelings through a series of ritualistic passive aggressive maneuvers as</p>
		<p><strong>quantité</strong> : 5</p>
		<p><strong>date</strong> : 18/6/2019</p>
		<p><strong>édition</strong> : 2</p>
		<p><strong>langue</strong> : anglais</p>
		<p><strong>nombre de page</strong> : 105</p>
		<p><strong>Catégorie</strong> : littérature</p>	
		<p><strong>ISBN</strong> : 1548218234235264</p>	
		<button type="button" class="btn btn-outline-danger">annuler la réservation de ce livre</button>
      </div>
    </div>
  </div>
  
  
  
</div> -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>