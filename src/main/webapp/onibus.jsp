<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Ônibus</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div>
        <jsp:include page="menu.jsp"/>
    </div>
    <br>
    <main>
        <h1>Ônibus</h1>
        <br>
        <form action="onibus" method="post">
            <div>
                <input type="text" name="placa" id="placa" placeholder="Placa">
                <input type="submit" value="Buscar" name="botao" id="buscar">
            </div>
            <div>
                <input type="number" name="ano" id="ano" placeholder="Ano">
            </div>
            <div>
                <input type="text" name="marca" id="marca" placeholder="Marca">
            </div>
            <div>
                <input type="text" name="descricao" id="descricao" placeholder="Descrição">
            </div>
            <div>
                <input type="submit" name="botao" id="Cadastrar" value="Cadastrar">
                <input type="submit" name="botao" id="Atualizar" value="Atualizar">
                <input type="submit" name="botao" id="Excluir" value="Excluir">
            </div>
            <div>
                <input type="submit" name="botao" id="Listar" value="Listar">
            </div>
            <br>
            <!-- tabela -->
            <div class="tabela_container">
                <c:if test="${not empty lista_onibus}">
                <table>
                    <thead>
                        <th>Placa</th>
                        <th>Ano</th>
                        <th>Marca</th>
                        <th>Descrição</th>
                    </thead>
                    <tbody>
                            <c:forEach items="${lista_onibus}" var="o">
                                <tr>
                                    <td><c:out value="${o.placa}"/></td>
                                    <td><c:out value="${o.ano}"/></td>
                                    <td><c:out value="${o.marca}"/></td>
                                    <td><c:out value="${o.descricao}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <!-- erro e saida -->
            <div>
                <c:if test="${not empty erro}">
                    <h3 class="erro"><c:out value="${erro}"/></h3>
                </c:if>
                <c:if test="${not empty saida}">
                    <h3 class="saida"><c:out value="${saida}"/></h3>
                </c:if>
            </div>
        </form>
    </main>
</body>
</html>