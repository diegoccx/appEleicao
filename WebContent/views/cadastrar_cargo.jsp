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
	<title>CADASTRAR UM CARGO</title>
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
	<h1>CADASTRAR UM CARGO</h1>
	<form id="form_usuario" action="cargo" method="post">
		<input type="hidden" name="opcao" value="guardar_cargo">
		<table border="1" class="highlight">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_box</i>
						<input id="nome_prefix" type="text" class="validate" campo-obrigatorio size="30" name="nome">
						<label for="nome_prefix">Nome do Cargo <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			
			
			<tr>
				<td>
					<div class="input-field col s5">
						<div class="left-align">
							
							<label>ELEIÇÃO <font color="red">(Obrigatório)</font></label>
						</div>
						<select name="id_eleicao" required="required" >
							<option value="" class="validate">Selecione a eleição... </option>
							<c:forEach var="eleicao" items="${listaEleicoes}">
							<option value="<c:out value="${eleicao.idEleicao}"></c:out>"><c:out value="${eleicao.nome}"></c:out></option>
							</c:forEach>						
						</select>
						
					</div>
				</td>
			</tr>
			
		<table>
			
		</table>
		<div class="center-align">
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar">SALVAR
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