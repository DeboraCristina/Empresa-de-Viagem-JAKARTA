package fate.debora.empresa_onibus.controller;

import fate.debora.empresa_onibus.model.DescricaoViagemDTO;
import fate.debora.empresa_onibus.model.Motorista;
import fate.debora.empresa_onibus.model.Onibus;
import fate.debora.empresa_onibus.model.Viagem;
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

@WebServlet("/viagem")
public class ViagemServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public ViagemServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("codigo");
        String codMotorista = request.getParameter("motorista");
        String placa = request.getParameter("onibus");
        String hrSaida = request.getParameter("hr_saida");
        String hrChegada = request.getParameter("hr_chegada");
        String partida = request.getParameter("partida");
        String destino = request.getParameter("destino");

        String erro = "";
        String saida = "";
        String tipoTab = "viagem";

        DescricaoViagemDTO descViagem = new DescricaoViagemDTO();
        Viagem v = new Viagem();
        List<Viagem> viagens = new ArrayList<>();

        if (!cmd.equalsIgnoreCase("listar"))
            v.setCodigo(Integer.parseInt(codigo));

        try
        {
            if (cmd.equalsIgnoreCase("cadastrar") || cmd.equalsIgnoreCase("atualizar"))
            {
                v.setHoraSaida(Integer.parseInt(hrSaida));
                v.setHoraChegada(Integer.parseInt(hrChegada));
                v.setPartida(partida);
                v.setDestino(destino);

                Motorista m = new Motorista();
                m.setCodigo(Integer.parseInt(codMotorista));
                m = buscarMotorista(m);
                v.setMotorista(m);

                Onibus o = new Onibus();
                o.setPlaca(placa);
                o = buscarOnibus(o);
                v.setOnibus(o);
            }

            if (cmd.equalsIgnoreCase("cadastrar"))
            {
                inserirViagem(v);
                saida = "Viagem cadastrada com sucesso! :)";
                v = new Viagem();
            }
            if (cmd.equalsIgnoreCase("atualizar"))
            {
                atualizarViagem(v);
                saida = "Viagem atualizada com sucesso! :)";
                v = new Viagem();
            }
            if (cmd.equalsIgnoreCase("excluir"))
            {
                excluirViagem(v);
                saida = "Viagem excluida com sucesso! :)";
                v = new Viagem();
            }

            if (cmd.equalsIgnoreCase("buscar"))
                v = buscarViagem(v);
            if (cmd.equalsIgnoreCase("descrição da viagem"))
                descViagem = buscarDescricaoViagem(v);
            if (cmd.equalsIgnoreCase("descrição do ônibus"))
                v = buscarDescricaoOnibus(v);

            if (cmd.equalsIgnoreCase("listar"))
                viagens = listarViagens();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            erro = e.getMessage();
        }
        finally
        {
            request.setAttribute("erro", erro);
            request.setAttribute("saida", saida);
            request.setAttribute("viagens", viagens);
            request.setAttribute("viagem", v);
            request.setAttribute("descViagem", descViagem);

            RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
            rd.forward(request, response);
        }
    }

    private Motorista buscarMotorista(Motorista m) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        return new Motorista(10, "Fulano", "Paulista");
    }

    private Onibus buscarOnibus(Onibus o) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        return new Onibus("666-xfse", "Volvo", "AAA", 1980);
    }


    private void inserirViagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void atualizarViagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
    }

    private void excluirViagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método

    }

    private List<Viagem> listarViagens() throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        List<Viagem> v = new ArrayList<>();
        Motorista m = new Motorista(100, "Cicrano", "Italiano");
        Onibus o = new Onibus("159-cfes", "Mercedes", "AAH", 1980);
        v.add(new Viagem(121, m, o, 10, 15, "São Paulo", "Brasilia"));
        v.add(new Viagem(122, m, o, 14, 20, "São Paulo", "Curitiba"));

        return v;
    }


    private Viagem buscarViagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        Motorista m = new Motorista(110, "Cicrano", "Italiano");
        Onibus o = new Onibus("169-cfes", "Mercedes", "AAH", 1980);
        return new Viagem(125, m, o, 14, 20, "São Paulo", "Curitiba");
    }
    private DescricaoViagemDTO buscarDescricaoViagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        return new DescricaoViagemDTO(102, "169-cfes", "14:00", "20:00", "São Paulo", "Curitiba");
    }
    private Viagem buscarDescricaoOnibus(Viagem v) throws SQLException, ClassNotFoundException
    {
        // TODO: Preencher método
        Onibus o = new Onibus("169-cfes", "Mercedes", "AAH", 1980);
        Motorista m = new Motorista(114, "Cicrano", "Italiano");
        return new Viagem(125, m, o, 23, 20, "São Paulo", "Curitiba");
    }
}