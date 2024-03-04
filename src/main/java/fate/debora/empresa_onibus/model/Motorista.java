package fate.debora.empresa_onibus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Motorista
{
    private int codigo;
    private String nome;
    private String naturalidade;

    @Override
    public String toString()
    {
        return this.nome;
    }
}
