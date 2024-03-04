<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Viagem</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div>
        <jsp:include page="menu.jsp"/>
    </div>
    <br>
    <main>
        <h1>Viagem</h1>
        <br>
        <form action="viagem" method="post">
            <div>
                <input type="number" min="0" name="codigo" id="codigo" placeholder="Código">
            </div>
            <div>
                <input type="submit" value="Buscar" name="botao" id="buscar">
                <input type="submit" value="Descrição da Viagem" name="botao" id="descViagem">
                <input type="submit" value="Descrição do Ônibus" name="botao" id="descOnibus">
            </div>
            <div>
                <select name="motorista" id="motorista">
                    <option value="0">Escolha um Motorista</option>
                    <c:forEach items="${motoristas}" var="m">
                        <c:if test="${(empty viagem) || (m.codigo ne viagem.motorista.codigo)}">
                            <option value="${m.codigo}"><c:out value="${m}"/></option>
                        </c:if>
                        <c:if test="${m.codigo eq viagem.motorista.codigo}">
                            <option selected="selected" value="${m.codigo}"><c:out value="${m}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
                <select name="onibus" id="onibus">
                    <option value="0">Escolha uma Placa</option>
                    <c:forEach items="${lista_onibus}" var="o">
                        <c:if test="${(empty viagem) || (o.codigo ne viagem.onibus.codigo)}">
                            <option value="${o.codigo}"><c:out value="${o}"/></option>
                        </c:if>
                        <c:if test="${o.codigo eq viagem.onibus.codigo}">
                            <option selected="selected" value="${o.codigo}"><c:out value="${o}"/></option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="number" min="0" max="24" name="hr_saida" id="hr_saida" placeholder="Hora Saída">
                <input type="number" min="0" max="24" name="hr_chegada" id="hr_chegada" placeholder="Hora Chegada">
            </div>
            <div>
                <input type="text" name="partida" id="partida" placeholder="Partida">
                <input type="text" name="destino" id="destino" placeholder="Destino">
            </div>
            <div>
                <input type="submit" name="botao" id="Cadastrar" value="Cadastrar">
                <input type="submit" name="botao" id="Atualizar" value="Atualizar">
                <input type="submit" name="botao" id="Excluir" value="Excluir">
            </div>
            <div>
                <input type="submit" name="botao" value="Listar Viagens">
            </div>
            <br>
            <!-- tabela -->
            <div class="tabela_container">
                <c:if test="${not empty viagens}">
                    <c:if test="${tipoTab eq 'viagem'}">
                        <table>
                            <jsp:include page="tabela_viagens.jsp"/>
                            <tbody>
                                <c:forEach items="${viagens}" var="v">
                                    <tr>
                                        <td><c:out value="${v.codigo}"/></td>
                                        <td><c:out value="${v.motorista}"/></td>
                                        <td><c:out value="${v.onibus}"/></td>
                                        <td><c:out value="${v.hr_saida}"/></td>
                                        <td><c:out value="${v.hr_chegada}"/></td>
                                        <td><c:out value="${v.partida}"/></td>
                                        <td><c:out value="${v.destino}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${tipoTab eq 'descOnibus'}">
                        <table>
                            <jsp:include page="tabela_desc_onibus.jsp"/>
                            <tbody>
                            <c:forEach items="${viagens}" var="v">
                                <tr>
                                    <td><c:out value="${v.codigo}"/></td>
                                    <td><c:out value="${v.motorista}"/></td>
                                    <td><c:out value="${v.onibus}"/></td>
                                    <td><c:out value="${v.onibus.marca}"/></td>
                                    <td><c:out value="${v.onibus.ano}"/></td>
                                    <td><c:out value="${v.onibus.descricao}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${tipoTab eq 'descViagem'}">
                        <table>
                            <jsp:include page="tabela_desc_viagem.jsp"/>
                            <tbody>
                            <c:forEach items="${desc_viagens}" var="dv">
                                <tr>
                                    <td><c:out value="${dv.codigo}"/></td>
                                    <td><c:out value="${dv.onibus}"/></td>
                                    <td><c:out value="${dv.hr_saida}"/></td>
                                    <td><c:out value="${dv.hr_chegada}"/></td>
                                    <td><c:out value="${dv.partida}"/></td>
                                    <td><c:out value="${dv.destino}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
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