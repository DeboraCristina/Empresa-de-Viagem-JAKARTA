package fate.debora.empresa_onibus.controller;

import fate.debora.empresa_onibus.model.Motorista;
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

@WebServlet("/motorista")
public class MotoristaServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public MotoristaServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String naturalidade = request.getParameter("naturalidade");

        String erro = "";
        String saida = "";
        Motorista m = new Motorista();
        List<Motorista> motoristas = new ArrayList<>();

        if (!cmd.equalsIgnoreCase("listar"))
            m.setCodigo(Integer.parseInt(codigo));

        if (cmd.equalsIgnoreCase("cadastrar") || cmd.equalsIgnoreCase("atualizar"))
        {
            m.setNome(nome);
            m.setNaturalidade(naturalidade);
        }

        try
        {
            if (cmd.equalsIgnoreCase("cadastrar"))
            {
                inserirMotorista(m);
                saida = "Motorista cadastrado com sucesso! :)";
                m = new Motorista();
            }
            if (cmd.equalsIgnoreCase("atualizar"))
            {
                atualizarMotorista(m);
                saida = "Motorista atualizado com sucesso! :)";
                m = new Motorista();
            }
            if (cmd.equalsIgnoreCase("excluir"))
            {
                excluirMotorista(m);
                saida = "Motorista excluido com sucesso! :)";
                m = new Motorista();
            }
            if (cmd.equalsIgnoreCase("buscar"))
                m = buscarMotorista(m);
            if (cmd.equalsIgnoreCase("listar"))
                motoristas = listarMotoristas();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("erro", erro);
            request.setAttribute("saida", saida);
            request.setAttribute("motorista", m);
            request.setAttribute("motoristas", motoristas);

            RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
            rd.forward(request, response);
        }
    }

    private void inserirMotorista(Motorista m) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void atualizarMotorista(Motorista m) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void excluirMotorista(Motorista m) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private Motorista buscarMotorista(Motorista m) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        return new Motorista(10, "Fulano", "Paulista");
    }

    private List<Motorista> listarMotoristas() throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        List<Motorista> m = new ArrayList<>();
        m.add(new Motorista(11, "Fulano 1", "Sulista"));
        m.add(new Motorista(12, "Fulano 2", "Paulista"));
        m.add(new Motorista(13, "Fulano 3", "Paulista"));
        m.add(new Motorista(14, "Fulano 4", "Bhaiano"));
        m.add(new Motorista(15, "Fulano 5", "Paulista"));

        return m;
    }
}