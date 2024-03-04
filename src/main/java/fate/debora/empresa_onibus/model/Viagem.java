package fate.debora.empresa_onibus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Viagem
{
    private int codigo;
    private Motorista motorista;
    private Onibus onibus;
    private int horaSaida;
    private int horaChegada;
    private String partida;
    private String destino;
}
