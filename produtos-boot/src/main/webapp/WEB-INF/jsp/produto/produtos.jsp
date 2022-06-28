<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>

    <title>Produtos - Listagem</title>
    
    <c:set value="${pageContext.request.contextPath}" var="contextPath"/>
    
    <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/css/small-business.css" rel="stylesheet">

</head>

<body>
    
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${contextPath}/produto">Produtos</a>
                    </li>
                    <li>
                    	<a href="${contextPath}/categoria">Categorias</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
				
				<h1>Produtos</h1>
				
				<p class="toolbar">
				
					<a class="create btn btn-default" href="${contextPath}/produto/form?page=produto-novo">Novo Produto</a>
	
					<span class="alert"></span>
				</p>
				
				<c:if test="${not empty messages}">
					<h3 class="alert alert-warning">${messages}</h3>
				</c:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th data-field="name">Nome</th>
							<th data-field="categoria">Categoria</th>
							<th data-field="marca">Marca</th>
							<th data-field="preco">Preço</th>
							<th class="actions" width="220">Ações</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${produtos}" var="produto">
						<tr>
							<td>${produto.nome}</td>
							<td>${produto.categoria.nomeCategoria}</td>
							<td>${produto.marca.nomeMarca}</td>
							<td>${produto.preco}</td>
							
							<td class="actions">
							
								<form:form action="${contextPath}/produto/${produto.id}" method="delete">
								
									<a class="btn btn-success btn-xs" href="${contextPath}/produto/${produto.id}">Detalhes</a>
									<a class="btn btn-warning btn-xs" href="${contextPath}/produto/form?page=produto-editar&id=${produto.id}">Editar</a>
									<input type="submit" value="Excluir" class="btn btn-danger btn-xs">
								</form:form>
								
							</td>
						</tr>
					</c:forEach>
                    </tbody>
				</table>
            </div>
        </div>
		<hr>
    </div>

    <!-- jQuery -->
    <script src="${contextPath}/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/js/bootstrap.min.js"></script>

</body>
</html>