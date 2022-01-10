<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


	<c:if test="${empty sessionScope['usuario']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="index.jsp" />
	</c:if>
	
	<meta charset="UTF-8">
	<title>Usuários</title>
</head>
<body>
<div class="container">
	<table class="yellow lighten-4">
	 	<tr>
	 		<td>
				<font size="3">
		 			Bem vindo <c:out value="${sessionScope.usuario.nome}"/>, deseja fazer <a href="logout">Logout?</a>
	 			</font>
	 		</td>
	 	</tr>
	</table>
	
	<h1>SISTEMA DE ELEIÇÃO ONLINE </h1>
	
	<c:if test="${!empty sessionScope.msgAviso }">
		<tr>
			<td class="center-align">
				<font size="5" color="<c:out value="${sessionScope.msgAvisoCor}"/>">
					<c:out value="${sessionScope.msgAviso}"/>
				</font>
			</td>
		</tr>
	</c:if>
	<table border="1">
		
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "eleicao?opcao=criar_eleicao">Cadastrar Eleição</a></td>
		</tr>
		
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "cargo?opcao=criar_cargo">Cadastrar Cargo</a></td>
		</tr>
		
			<tr>
			<td> <a class="waves-effect waves-light btn" href= "candidato?opcao=criar_candidato">Cadastrar Candidato</a></td>
		</tr>
		
			<tr>
			<td> <a class="waves-effect waves-light btn" href= "candidatoVoto?opcao=criar_voto">Votar</a></td>
		</tr>
		
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "candidatoVoto?opcao=resultado_votacao">RESULTADO</a></td>
		</tr>
		
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "usuario?opcao=criar">Registrar um Usuário</a></td>
		</tr>
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "usuario?opcao=listar">Listar todos os Usuários</a></td>
		</tr>
	
	</table>
	
</div>
<c:set var="msgAviso" value="" scope="session" />	


</body>
</html>