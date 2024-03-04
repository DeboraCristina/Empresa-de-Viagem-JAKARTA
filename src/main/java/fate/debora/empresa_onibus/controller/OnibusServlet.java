package fate.debora.empresa_onibus.controller;

import fate.debora.empresa_onibus.model.Onibus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/onibus")
public class OnibusServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public OnibusServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher("onibus.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String placa = request.getParameter("botao");
        String marca = request.getParameter("botao");
        String descricao = request.getParameter("botao");
        String ano = request.getParameter("ano");

        String erro = "";
        String saida = "";
        Onibus o = new Onibus();
        List<Onibus> lista_onibus = new ArrayList<>();

        if (!cmd.equalsIgnoreCase("listar"))
            o.setPlaca(placa);
        if (cmd.equalsIgnoreCase("cadastrar") || cmd.equalsIgnoreCase("atualizar"))
        {
            o.setMarca(marca);
            o.setDescricao(descricao);
            o.setAno(Integer.parseInt(ano));
        }
        try
        {
            if (cmd.equalsIgnoreCase("cadastrar"))
            {
                inserirOnibus(o);
                saida = "Ônibus cadastrado com sucesso! :)";
                o = new Onibus();
            }
            if (cmd.equalsIgnoreCase("atualizar"))
            {
                atualizarOnibus(o);
                saida = "Ônibus atualizado com sucesso! :)";
                o = new Onibus();
            }
            if (cmd.equalsIgnoreCase("excluir"))
            {
                excluirOnibus(o);
                saida = "Ônibus excluido com sucesso! :)";
                o = new Onibus();
            }
            if (cmd.equalsIgnoreCase("buscar"))
                o = buscarOnibus(o);
            if (cmd.equalsIgnoreCase("listar"))
                lista_onibus = listarOnibus();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("erro", erro);
            request.setAttribute("saida", saida);
            request.setAttribute("onibus", o);
            request.setAttribute("lista_onibus", lista_onibus);

            RequestDispatcher rd = request.getRequestDispatcher("onibus.jsp");
            rd.forward(request, response);
        }
    }

    private void inserirOnibus(Onibus o) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void atualizarOnibus(Onibus o) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void excluirOnibus(Onibus o) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private Onibus buscarOnibus(Onibus o) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        return new Onibus("666-xfse", "Volvo", "AAA", 1980);
    }

    private List<Onibus> listarOnibus() throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        List<Onibus> o = new ArrayList<>();

        o.add(new Onibus("123-xgxx", "Mercedes", "Limpo", 2021));
        o.add(new Onibus("124-xxxx", "Mercedes", "Sujo", 2022));
        o.add(new Onibus("125-xaxx", "Mercedes", "Arrumado", 2023));
        o.add(new Onibus("126-axxx", "Mercedes", "Limpo", 2024));

        return o;
    }
}