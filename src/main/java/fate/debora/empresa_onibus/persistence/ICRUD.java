package fate.debora.empresa_onibus.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICRUD<T>
{
    public void insert (T t)	throws SQLException, ClassNotFoundException;
    public void update (T t)	throws SQLException, ClassNotFoundException;
    public void delete (T t)    throws SQLException, ClassNotFoundException;
    public T find (T t)         throws SQLException, ClassNotFoundException;
    public List<T> list ()    	    throws SQLException, ClassNotFoundException;
}
