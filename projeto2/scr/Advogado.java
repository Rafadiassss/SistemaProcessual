package scr;

public class Advogado {
    int numeroOAB;
    String nome;
    String email;
    String telefone;
    String cpfOuCnpj;

    // Construtor da classe Advogado
    public Advogado(int numeroOAB, String nome, String email, String telefone, String cpfOuCnpj) {
        this.numeroOAB = numeroOAB;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpfOuCnpj = cpfOuCnpj;
    }

    // Método para exibir as informações do advogado
    @Override
    public String toString() {
        return "Advogado [Número OAB: " + numeroOAB + ", Nome: " + nome + ", E-mail: " + email +
            ", Telefone: " + telefone + ", CPF/CNPJ: " + cpfOuCnpj + "]";
    }
}
