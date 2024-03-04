package fate.debora.empresa_onibus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DescricaoViagemDTO
{
    private int codigo;
    private String placaOnibus;
    private String horaSaida_Formatada;
    private String horaChegada_Formatada;
    private String partida;
    private String destino;
}
