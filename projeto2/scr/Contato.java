package scr;

public class Contato {
    int id;
    String nome;
    String email;
    String cpfCnpj;
    String dataNascimento;

    public Contato(int id, String nome, String email, String cpfCnpj, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Contato ID: " + id + ", Nome: " + nome;
    }
}
