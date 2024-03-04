<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Motorista</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div>
        <jsp:include page="menu.jsp"/>
    </div>
    <br>
    <main>
        <h1>Motorista</h1>
        <br>
        <form action="motorista" method="post">
            <div>
                <input type="number" min="0" name="codigo" id="codigo" placeholder="Código">
                <input type="submit" value="Buscar" name="botao" id="buscar">
            </div>
            <div>
                <input type="text" name="nome" id="nome" placeholder="Nome">
            </div>
            <div>
                <input type="text" name="naturalidade" id="naturalidade" placeholder="Naturalidade">
            </div>
            <div>
                <input type="submit" name="botao" id="Cadastrar" value="Cadastrar">
                <input type="submit" name="botao" id="Atualizar" value="Atualizar">
                <input type="submit" name="botao" id="Excluir" value="Excluir">
            </div>
            <div>
                <input type="submit" name="botao" value="Listar">
            </div>
            <br>
            <!-- tabela -->
            <div class="tabela_container">
                <c:if test="${not empty motoristas}">
                <table>
                    <thead>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Naturalidade</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>a</td>
                            <td>a</td>
                            <td>a</td>
                        </tr>
                        <tr>
                            <td>a</td>
                            <td>a</td>
                            <td>a</td>
                        </tr>
                        <tr>
                            <td>a</td>
                            <td>a</td>
                            <td>a</td>
                        </tr>
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