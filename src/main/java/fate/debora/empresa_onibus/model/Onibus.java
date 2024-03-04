package fate.debora.empresa_onibus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Onibus
{
    private String placa;
    private String marca;
    private String descricao;
    private int ano;

    @Override
    public String toString()
    {
        return this.placa;
    }
}
