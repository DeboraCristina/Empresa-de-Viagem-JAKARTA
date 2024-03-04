package fate.debora.empresa_onibus.persistence;

import fate.debora.empresa_onibus.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO implements ICRUD<Viagem>, IView
{
    private GenericDAO gdao;

    public ViagemDAO(GenericDAO gdao)
    {
        this.gdao = gdao;
    }

    @Override
    public void insert(Viagem viagem) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO viagem(codigo, placa_onibus, codigo_motorista, hora_saida, ");
        sqlQuery.append("hora_chegada, partida, destino) VALUES ");
        sqlQuery.append("(?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement ps = c.prepareStatement(sqlQuery.toString());
        ps.setInt   (1, viagem.getCodigo());
        ps.setString(2, viagem.getOnibus().getPlaca());
        ps.setInt   (3, viagem.getMotorista().getCodigo());
        ps.setInt   (4, viagem.getHoraSaida());
        ps.setInt   (5, viagem.getHoraChegada());
        ps.setString(6, viagem.getPartida());
        ps.setString(7, viagem.getDestino());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void update(Viagem viagem) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "UPDATE viagem SET placa_onibus=?, codigo_motorista=?, hora_saida=?, " +
                "hora_chegada=?, partida=?, destino=? " +
                "WHERE codigo=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, viagem.getOnibus().getPlaca());
        ps.setInt   (2, viagem.getMotorista().getCodigo());
        ps.setInt   (3, viagem.getHoraSaida());
        ps.setInt   (4, viagem.getHoraChegada());
        ps.setString(5, viagem.getPartida());
        ps.setString(6, viagem.getDestino());
        ps.setInt   (7, viagem.getCodigo());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void delete(Viagem viagem) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "DELETE viagem WHERE codigo=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setInt(1, viagem.getCodigo());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Viagem find(Viagem viagem) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append("SELECT v.codigo AS codViagem, v.hora_saida AS hsViagem, v.hora_chegada AS hcViagem, ");
        sqlQuery.append("v.partida AS partidaViagem, v.destino AS destinoViagem, ");
        sqlQuery.append("m.codigo AS codMoto, m.nome AS nomeMoto, m.naturalidade AS natuMoto, ");
        sqlQuery.append("SUBSTRING(o.placa,1, 3) + '-' + SUBSTRING(o.placa,4, 7) AS placaOnibus, ");
        sqlQuery.append("o.ano AS anoOnibus, o.marca AS marcaOnibus, o.descricao AS descOnibus ");
        sqlQuery.append("FROM viagem v, motorista m, onibus o ");
        sqlQuery.append("WHERE v.codigo_motorista = m.codigo AND v.placa_onibus = o.placa ");
        sqlQuery.append("AND v.codigo=?");
        PreparedStatement ps = c.prepareStatement(sqlQuery.toString());
        ps.setInt(1, viagem.getCodigo());
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            Motorista m = new Motorista();
            Onibus o = new Onibus();
            m.setCodigo(rs.getInt("codMoto"));
            m.setNome(rs.getString("nomeMoto"));
            m.setNaturalidade(rs.getString("natuMoto"));

            o.setPlaca(rs.getString("placaOnibus"));
            o.setMarca(rs.getString("anoOnibus"));
            o.setAno(rs.getInt("marcaOnibus"));
            o.setDescricao(rs.getString("descOnibus"));

            viagem.setCodigo(rs.getInt("codViagem"));
            viagem.setHoraSaida(rs.getInt("hsViagem"));
            viagem.setHoraChegada(rs.getInt("hcViagem"));
            viagem.setPartida(rs.getString("partidaViagem"));
            viagem.setDestino(rs.getString("destinoViagem"));
            viagem.setMotorista(m);
            viagem.setOnibus(o);
        }

        rs.close();
        ps.close();
        c.close();
        return viagem;
    }

    @Override
    public List<Viagem> list() throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append("SELECT v.codigo AS codViagem, v.hora_saida AS hsViagem, v.hora_chegada AS hcViagem, ");
        sqlQuery.append("v.partida AS partidaViagem, v.destino AS destinoViagem, ");
        sqlQuery.append("m.codigo AS codMoto, m.nome AS nomeMoto, m.naturalidade AS natuMoto, ");
        sqlQuery.append("SUBSTRING(o.placa,1, 3) + '-' + SUBSTRING(o.placa,4, 7) AS placaOnibus, ");
        sqlQuery.append("o.ano AS anoOnibus, o.marca AS marcaOnibus, o.descricao AS descOnibus ");
        sqlQuery.append("FROM viagem v, motorista m, onibus o ");
        sqlQuery.append("WHERE v.codigo_motorista = m.codigo AND v.placa_onibus = o.placa");
        PreparedStatement ps = c.prepareStatement(sqlQuery.toString());

        List<Viagem> viagens = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Motorista m = new Motorista();
            Onibus o = new Onibus();
            m.setCodigo(rs.getInt("codMoto"));
            m.setNome(rs.getString("nomeMoto"));
            m.setNaturalidade(rs.getString("natuMoto"));

            o.setPlaca(rs.getString("placaOnibus"));
            o.setMarca(rs.getString("anoOnibus"));
            o.setAno(rs.getInt("marcaOnibus"));
            o.setDescricao(rs.getString("descOnibus"));

            Viagem viagem = new Viagem();
            viagem.setCodigo(rs.getInt("codViagem"));
            viagem.setHoraSaida(rs.getInt("hsViagem"));
            viagem.setHoraChegada(rs.getInt("hcViagem"));
            viagem.setPartida(rs.getString("partidaViagem"));
            viagem.setDestino(rs.getString("destinoViagem"));
            viagem.setMotorista(m);
            viagem.setOnibus(o);
        }

        rs.close();
        ps.close();
        c.close();
        return viagens;
    }

    @Override
    public Viagem view_descricao_onibus(Viagem v) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT codViagem, nomeMotorista, placaOnibus, marcaOnibus, ");
        sqlQuery.append("anoOnibus, descOnibus FROM v_descricao_onibus ");
        sqlQuery.append("WHERE codViagem = ?");
        PreparedStatement ps = c.prepareStatement(sqlQuery.toString());
        ps.setInt(1, v.getCodigo());
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            v.setCodigo(rs.getInt("codViagem"));

            Onibus o = new Onibus();
            o.setPlaca(rs.getString("placaOnibus"));
            o.setMarca(rs.getString("marcaOnibus"));
            o.setAno(rs.getInt("anoOnibus"));
            o.setDescricao(rs.getString("descOnibus "));

            Motorista m = new Motorista();
            m.setNome(rs.getString("nomeMotorista"));

            v.setOnibus(o);
            v.setMotorista(m);
        }

        rs.close();
        ps.close();
        c.close();
        return v;
    }

    @Override
    public DescricaoViagemDTO view_descricao_viagem(Viagem v) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        StringBuffer sqlQuery = new StringBuffer();
        sqlQuery.append("SELECT codViagem, placaOnibus, hrSaida, hrChegada, ");
        sqlQuery.append("partida, destino FROM v_descricao_viagem ");
        sqlQuery.append("WHERE codViagem = ?");
        PreparedStatement ps = c.prepareStatement(sqlQuery.toString());
        ps.setInt(1, v.getCodigo());
        ResultSet rs = ps.executeQuery();

        DescricaoViagemDTO descViagemDTO = null;
        if (rs.next())
        {
            descViagemDTO.setCodigo(rs.getInt("codViagem"));
            descViagemDTO.setPlacaOnibus(rs.getString("placaOnibus"));
            descViagemDTO.setHoraSaida_Formatada(rs.getString("hrSaida"));
            descViagemDTO.setHoraChegada_Formatada(rs.getString("hrChegada"));
            descViagemDTO.setPartida(rs.getString("partida"));
            descViagemDTO.setDestino(rs.getString("destino"));
        }

        rs.close();
        ps.close();
        c.close();
        return descViagemDTO;
    }
}
