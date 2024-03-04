package fate.debora.empresa_onibus.persistence;

import fate.debora.empresa_onibus.model.Onibus;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class OnibusDAO implements ICRUD<Onibus>
{
    private GenericDAO gdao;

    public OnibusDAO(GenericDAO gdao)
    {
        this.gdao = gdao;
    }


    @Override
    public void insert(Onibus onibus) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "INSERT INTO onibus(placa, marca, ano, descricao) VALUES" +
                "(?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, onibus.getPlaca());
        ps.setString(2, onibus.getMarca());
        ps.setInt   (3, onibus.getAno());
        ps.setString(4, onibus.getDescricao());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void update(Onibus onibus) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "UPDATE onibus SET marca=?, ano=?, " +
                "descricao=? WHERE placa=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, onibus.getMarca());
        ps.setInt   (2, onibus.getAno());
        ps.setString(3, onibus.getDescricao());
        ps.setString(4, onibus.getPlaca());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void delete(Onibus onibus) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "DELETE onibus WHERE placa=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, onibus.getPlaca());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Onibus find(Onibus onibus) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "SELECT placa, marca, ano, descricao FROM onibus " +
                "WHERE placa=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, onibus.getPlaca());
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            onibus.setPlaca(rs.getString("placa"));
            onibus.setMarca(rs.getString("marca"));
            onibus.setDescricao(rs.getString("descricao"));
            onibus.setAno(rs.getInt("ano"));
        }

        rs.close();
        ps.close();
        c.close();
        return onibus;
    }

    @Override
    public List<Onibus> list() throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "SELECT SUBSTRING(o.placa,1, 3) + '-' + SUBSTRING(o.placa,4, 7) AS placa, " +
            "marca, ano, descricao FROM onibus o";
        PreparedStatement ps = c.prepareStatement(sqlQuery);

        List<Onibus> onibusList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Onibus onibus = new Onibus();
            onibus.setPlaca(rs.getString("placa"));
            onibus.setMarca(rs.getString("marca"));
            onibus.setDescricao(rs.getString("descricao"));
            onibus.setAno(rs.getInt("ano"));
            onibusList.add(onibus);
        }

        rs.close();
        ps.close();
        c.close();
        return onibusList;
    }
}
