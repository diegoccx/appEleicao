<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script> 
	<script src="js/validarFormCadastroUsuario.js" type="text/javascript"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		
	<meta charset="UTF-8">
	<title>REALIZAR VOTAÇÃO</title>
	<script type="text/javascript">
		  $(document).ready(function(){
		    $('select').formSelect();
		  });
	</script>

</head>
<body>
<div class="container">

<c:if test="${!empty sessionScope['usuario']}">
<table class="yellow lighten-4">
	<tr>
 		<td>
			<font size="3">
				Bem vindo <c:out value="${sessionScope.usuario.nome}"/>, deseja fazer <a href="logout">Logout?</a>
			</font>
		</td>
	</tr>
</table>
</c:if>
	<h1>REALIZAR VOTAÇÃO</h1>
	 <c:out value="${sessionScope.usuario.nome}"/>
	  <c:out value="${sessionScope.usuario.cpf}"/>
	<form id="form_candidatoVoto" action="candidatoVoto" method="post">
		<input type="hidden" name="opcao" value="guardar_voto">
		<table border="1" class="highlight">
			
			
			<tr>
				<td>
					<div class="input-field col s5">
						<div class="left-align">
							
							<label>CARGO-VOTACAO</label>
						</div>
						<c:forEach var="cargoForm1" items="${listaCargosVotoForm}" varStatus="itemLoop">
						<c:out value="${cargoForm1.nomeEleicao}"/>
						<c:out value="${cargoForm1.nomeCargo}"/>
						<select name="id_cargoForm${itemLoop.index}" required="required">
							<option value="">Selecione o candidato...</option>
							<c:forEach var="candidato" items="${cargoForm1.candidatos}">
							<option value="<c:out value="${candidato.idCandidato}"></c:out>"><c:out value="${candidato.nome}"></c:out></option>
							</c:forEach>						
						</select>
						</c:forEach>
					</div>
				</td>
			</tr>
			
		<table>
			
		</table>
		<div class="center-align">
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar">VOTAR
		    <i class="material-icons right">add_circle_outline</i>
		  </button>
		</div>
		<br>
		<c:if test="${!empty sessionScope['usuario']}">
			<div class="center-align">
				<a class="waves-effect waves-light btn-large" href= "usuario?opcao=voltar&view=principal.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
			</div>
		</c:if>
		<c:if test="${empty sessionScope['usuario']}">
			<div class="center-align">
				<a class="waves-effect waves-light btn-large" href= "usuario?opcao=voltar"><i class="material-icons right">arrow_back</i>Voltar</a>
			</div>
		</c:if>
		<br>
	</form>
</div>
</body>
</html>