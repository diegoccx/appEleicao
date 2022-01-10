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
	<script>
var loadFile = function(event) {
	var image = document.getElementById('outputFoto');
	image.src = URL.createObjectURL(event.target.files[0]);
};
</script>	

<script>
	function validate(form){
    var value = form.file.value,
        ext = value.split(".").pop();

    if( !value ) {
        alert("Carregue a foto do candidato !");
        return ( false );
    }
   

    return ( true );
}
	</script>


	<meta charset="UTF-8">
	<title>CADASTRAR UM CANDIDATO</title>
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
	<h1>CADASTRAR UM CANDIDATO</h1>
	<form id="form_usuario" action="candidato" method="post" enctype="multipart/form-data" onsubmit="javascript:return validate(this);">
		<input type="hidden" name="opcao" value="guardar_candidato">
		<table border="1" class="highlight">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_box</i>
						<input id="nome_prefix" type="text" class="validate" campo-obrigatorio size="30" name="nome">
						<label for="nome_prefix">Nome do Candidato <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s5">
						<div class="left-align">
							
							<label>CARGO-ELEIÇÃO <font color="red">(Obrigatório)</font></label>
						</div>
						
						<select name="id_cargoForm0" id="cargoSelecao" required="required">
							<option value="">Selecione o cargo...</option>
							<c:forEach var="cargoForm" items="${listaCargosForm}">
							<option value="<c:out value="${cargoForm.idCargoEleicao}"></c:out>"><c:out value="${cargoForm.cargoEleicaoNome}"></c:out></option>
							</c:forEach>						
						</select>
						
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s5">
			<p><input type="file"  accept="image/*" name="photo" id="file"  onchange="loadFile(event)" style="display: none;"></p>
<p><label for="file" style="cursor: pointer;">FOTO DO CANDIDATO <font color="red">(Obrigatório)</font></label></p>
<p><img id="outputFoto" width="300" /></p>
</div>
				</td>
			</tr>


		
			
		<table>
			
		</table>
		<div class="center-align">
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar">Guardar
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