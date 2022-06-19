<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>e-chambre visiteur</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body> 
     <!-- 	nav bar--> 
 	<nav class="navbar navbar-light bg-info justify-content-between">
  		 <a class="navbar-brand"><bold>E-CHAMBRE</bold></a>
		 <div>
		    <a href="/e-chambre1/seConnecter.do" type="button" class="btn btn-outline-success m-2">se connecter</a>
		  	<a href="/e-chambre1/sInscrire.do" type="button" class="btn btn-outline-success m-2">s'inscrire</a>
		  </div>
	</nav>
	<br>
	
	<!-- search bar -->
	<div class="navbar navbar-light bg-wight">
  		<div class="container-fluid justify-content-center">
		    <form action="/e-chambre1/visiteur.do" class="d-flex" method="get">
		      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="motCle">
		      <button class="btn btn-outline-primary" type="submit">Search</button>
		    </form>
  		</div>
	</div>
	
	<!-- table of books -->
	<div classe="container">
	<table class="table ">
  <thead>
    <tr>
      <th scope="col">nom de chambre</th>
      <th scope="col">prix</th>
      
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${chambres }" var="chambre">
  	<tr>
      <th scope="row">${chambre.nom }</th>
      <td>${chambre.prix }</td>
      
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
</body>
</html>