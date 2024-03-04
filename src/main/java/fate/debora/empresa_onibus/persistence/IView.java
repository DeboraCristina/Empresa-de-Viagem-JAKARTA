package fate.debora.empresa_onibus.persistence;

import fate.debora.empresa_onibus.model.DescricaoViagemDTO;
import fate.debora.empresa_onibus.model.Viagem;

import java.sql.SQLException;

public interface IView
{
    public Viagem view_descricao_onibus(Viagem v) throws SQLException, ClassNotFoundException;
    public DescricaoViagemDTO view_descricao_viagem(Viagem v) throws SQLException, ClassNotFoundException;
}
