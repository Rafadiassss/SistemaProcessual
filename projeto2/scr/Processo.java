package scr;

public class Processo {
    int numeroProcesso;
    String tipoAcao;
    int autorId;
    int reu;
    int advogadoId;  // Se for um id de advogado, deve ser do tipo int

    // Construtor da classe Processo
    public Processo(int numeroProcesso, String tipoAcao, int autorId, int reu, int advogadoId) {
        this.numeroProcesso = numeroProcesso;
        this.tipoAcao = tipoAcao;
        this.autorId = autorId;
        this.reu = reu;
        this.advogadoId = advogadoId;
    }

    // Método para exibir as informações do processo
    @Override
    public String toString() {
        return "Processo [ID: " + numeroProcesso + ", Tipo de Ação: " + tipoAcao +
            ", Autor ID: " + autorId + ", Réu ID: " + reu + ", Advogado ID: " + advogadoId + "]";
    }
}
