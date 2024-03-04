package fate.debora.empresa_onibus.persistence;

import fate.debora.empresa_onibus.model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO implements ICRUD<Motorista>
{
    private GenericDAO gdao;

    public MotoristaDAO(GenericDAO genericDAO)
    {
        this.gdao = genericDAO;
    }

    @Override
    public void insert(Motorista motorista) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "INSERT INTO motorista(codigo, nome, naturalidade) VALUES" +
                "(?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setInt(1, motorista.getCodigo());
        ps.setString(2, motorista.getNome());
        ps.setString(3, motorista.getNaturalidade());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void update(Motorista motorista) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "UPDATE motorista SET nome=?, naturalidade=? WHERE codigo=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setString(1, motorista.getNome());
        ps.setString(2, motorista.getNaturalidade());
        ps.setInt   (3, motorista.getCodigo());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void delete(Motorista motorista) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "DELETE motorista WHERE codigo=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setInt(1, motorista.getCodigo());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Motorista find(Motorista motorista) throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "SELECT codigo, nome, naturalidade FROM motorista WHERE codigo=?";
        PreparedStatement ps = c.prepareStatement(sqlQuery);
        ps.setInt(1, motorista.getCodigo());
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            motorista.setCodigo(rs.getInt("codigo"));
            motorista.setNome(rs.getString("nome"));
            motorista.setNaturalidade(rs.getString("naturalidade"));
        }

        rs.close();
        ps.close();
        c.close();
        return motorista;
    }

    @Override
    public List<Motorista> list() throws SQLException, ClassNotFoundException
    {
        Connection c = gdao.getConnection();
        String sqlQuery = "SELECT codigo, nome, naturalidade FROM motorista";
        PreparedStatement ps = c.prepareStatement(sqlQuery);

        List<Motorista> motoristas = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Motorista motorista = new Motorista();
            motorista.setCodigo(rs.getInt("codigo"));
            motorista.setNome(rs.getString("nome"));
            motorista.setNaturalidade(rs.getString("naturalidade"));
            motoristas.add(motorista);
        }

        rs.close();
        ps.close();
        c.close();
        return motoristas;
    }
}
