package scr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    // Método para listar todos os processos
    public static void listarTodosProcessos(String path) {

        try {
            File dir = new File(path);
            File[] arquivos = dir.listFiles();

            if (arquivos != null && arquivos.length > 0) {
                System.out.println("\n--- Lista de Processos ---");
                for (File arquivo : arquivos) {
                    List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
                    for (String linha : linhas) {
                        System.out.println(linha);
                    }
                }
            } else {
                System.out.println("Nenhum processo encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar os processos: " + e.getMessage());
        }
    }
    // Método para buscar um processo pelo número e exibir todas as informações
public static void buscarProcesso(int numeroProcesso, String path) {
    try {
        File dir = new File(path);
        File[] arquivos = dir.listFiles();

        boolean encontrado = false;
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
                for (String linha : linhas) {
                    if (linha.contains("Número do Processo: " + numeroProcesso)) { // Busca pelo Número do Processo
                        System.out.println("\nProcesso encontrado! Informações completas:");
                        for (String info : linhas) { // Exibe todas as informações do arquivo
                            System.out.println(info);
                        }
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) break;
            }
        }

        if (!encontrado) {
            System.out.println("Processo com o número " + numeroProcesso + " não encontrado.");
        }
    } catch (IOException e) {
        System.out.println("Erro ao buscar o processo: " + e.getMessage());
    }
}
    // Método para gravar um processo
    public static void gravarProcesso(Processo processo, String path, String pathAdvogados) throws IOException {
        if (!verificarAdvogadoExiste(processo.advogadoId, pathAdvogados)) {
            System.out.println("Erro: Advogado com Número OAB " + processo.advogadoId + " não encontrado.");
            return;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();  // Cria o diretório se não existir
        }

        File file = new File(dir, processo.numeroProcesso + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Número do Processo: " + processo.numeroProcesso);
            writer.newLine();
            writer.write("Tipo de Ação: " + processo.tipoAcao);
            writer.newLine();
            writer.write("Autor: " + processo.autorId);
            writer.newLine();
            writer.write("Réu: " + processo.reu);
            writer.newLine();
            writer.write("Advogado: " + processo.advogadoId);
        }
    }
    // Método para remover um processo
    public static void removerProcesso(int numeroProcesso, String path) {
        File file = new File(path + numeroProcesso + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Processo removido com sucesso!");
            } else {
                System.out.println("Erro ao remover o processo.");
            }
        } else {
            System.out.println("Processo não encontrado.");
        }
    }
// Método para reescrever um processo existente
public static void reescreverProcesso(int numeroProcesso, Processo novoProcesso, String path) {
    File file = new File(path, numeroProcesso + ".txt");

    if (!file.exists()) {
        System.out.println("Processo com Número " + numeroProcesso + " não encontrado.");
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("Número do Processo: " + novoProcesso.numeroProcesso);
        writer.newLine();
        writer.write("Tipo de Ação: " + novoProcesso.tipoAcao);
        writer.newLine();
        writer.write("Autor: " + novoProcesso.autorId);
        writer.newLine();
        writer.write("Réu: " + novoProcesso.reu);
        writer.newLine();
        writer.write("Advogado: " + novoProcesso.advogadoId);
        System.out.println("Processo atualizado com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao atualizar o processo: " + e.getMessage());
    }
}

public static List<Contato> obterListaDeContatos(String path) {
    List<Contato> contatos = new ArrayList<>();
    try {
        File dir = new File(path);
        File[] arquivos = dir.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
                if (!linhas.isEmpty()) {
                    Contato contato = criarContatoAPartirDeLinhas(linhas);
                    if (contato != null) {
                        contatos.add(contato);
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao listar os contatos: " + e.getMessage());
    }
    return contatos;
}

private static Contato criarContatoAPartirDeLinhas(List<String> linhas) {
    try {
        int id = Integer.parseInt(linhas.get(0).split(": ")[1]);
        String nome = linhas.get(1).split(": ")[1];
        String dataNascimento = linhas.get(2).split(": ")[1];
        String email = linhas.get(3).split(": ")[1];
        String cpfCnpj = linhas.get(4).split(": ")[1];
        return new Contato(id, nome, dataNascimento, email, cpfCnpj);
    } catch (Exception e) {
        System.out.println("Erro ao processar contato: " + e.getMessage());
        return null;
    }
}

public static boolean verificarContatoExiste(int id, String path) {
    List<Contato> contatos = obterListaDeContatos(path);
    for (Contato contato : contatos) {
        if (contato.id == id) {
            return true;
        }
    }
    return false;
}

public static void gravarContato(Contato contato, String path) throws IOException {
    File dir = new File(path);
    if (!dir.exists()) {
        dir.mkdirs();
    }

    File file = new File(dir, contato.id + ".txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("ID: " + contato.id);
        writer.newLine();
        writer.write("Nome: " + contato.nome);
        writer.newLine();
        writer.write("Data de Nascimento: " + contato.dataNascimento);
        writer.newLine();
        writer.write("E-mail: " + contato.email);
        writer.newLine();
        writer.write("CPF ou CNPJ: " + contato.cpfCnpj);
    }
}
public static void removerContato(int id, String path) {
    File file = new File(path + id + ".txt");
    if (file.exists() && file.delete()) {
        System.out.println("Contato removido com sucesso!");
    } else {
        System.out.println("Erro ao remover o contato ou contato não encontrado.");
    }
}

public static void reescreverContato(int id, Contato novoContato, String path) {
    File file = new File(path, id + ".txt");
    if (!file.exists()) {
        System.out.println("Contato com ID " + id + " não encontrado.");
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("ID: " + novoContato.id);
        writer.newLine();
        writer.write("Nome: " + novoContato.nome);
        writer.newLine();
        writer.write("Data de Nascimento: " + novoContato.dataNascimento);
        writer.newLine();
        writer.write("E-mail: " + novoContato.email);
        writer.newLine();
        writer.write("CPF ou CNPJ: " + novoContato.cpfCnpj);
        System.out.println("Contato atualizado com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao atualizar o contato: " + e.getMessage());
    }
}
public static void buscarContato(int id, String path) {
    try {
    File dir = new File(path);
    File[] arquivos = dir.listFiles();

    boolean encontrado = false;
    if (arquivos != null) {
        for (File arquivo : arquivos) {
            List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
            for (String linha : linhas) {
                if (linha.contains("ID: " + id)) {  // Busca pelo ID no arquivo
                    System.out.println("\nContato encontrado! Informações completas:");
                    for (String info : linhas) {  // Exibe todas as linhas do arquivo
                        System.out.println(info);
                    }
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) break;
        }
    }

    if (!encontrado) {
        System.out.println("Contato com o ID " + id + " não encontrado.");
    }
} catch (IOException e) {
    System.out.println("Erro ao buscar o contato: " + e.getMessage());
}
}

// Método para listar todos os advogados
public static void listarTodosAdvogados(String path) {
    try {
        File dir = new File(path);
        File[] arquivos = dir.listFiles();

        if (arquivos != null && arquivos.length > 0) {
            System.out.println("\n--- Lista de Advogados ---");
            for (File arquivo : arquivos) {
                List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
                for (String linha : linhas) {
                    System.out.println(linha);
                }
            }
        } else {
            System.out.println("Nenhum advogado encontrado.");
        }
    } catch (IOException e) {
        System.out.println("Erro ao listar os advogados: " + e.getMessage());
    }
}
// Método para buscar um advogado pelo Número OAB e exibir todas as informações
public static void buscarAdvogado(int numeroOAB, String path) {
    try {
        File dir = new File(path);
        File[] arquivos = dir.listFiles();

        boolean encontrado = false;
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                List<String> linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
                for (String linha : linhas) {
                    if (linha.contains("Número OAB: " + numeroOAB)) { // Busca pelo Número OAB
                        System.out.println("\nAdvogado encontrado! Informações completas:");
                        for (String info : linhas) { // Exibe todas as informações do arquivo
                            System.out.println(info);
                        }
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) break;
            }
        }

        if (!encontrado) {
            System.out.println("Advogado com o número OAB " + numeroOAB + " não encontrado.");
        }
    } catch (IOException e) {
        System.out.println("Erro ao buscar o advogado: " + e.getMessage());
    }
}
// Método para gravar um advogado
public static void gravarAdvogado(Advogado advogado, String path) throws IOException {
    File dir = new File(path);
    if (!dir.exists()) {
        dir.mkdirs();  // Cria o diretório se não existir
    }

    File file = new File(dir, advogado.numeroOAB + ".txt");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("Número OAB: " + advogado.numeroOAB);
        writer.newLine();
        writer.write("Nome: " + advogado.nome);
        writer.newLine();
        writer.write("E-mail: " + advogado.email);
        writer.newLine();
        writer.write("Telefone: " + advogado.telefone);
        writer.newLine();
        writer.write("CPF ou CNPJ: " + advogado.cpfOuCnpj);
    }
}
public static void removerAdvogado(int numeroOAB, String path) {
    File file = new File(path + numeroOAB + ".txt");
    if (file.exists()) {
        if (file.delete()) {
            System.out.println("Advogado removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o advogado.");
        }
    } else {
        System.out.println("Advogado não encontrado.");
    }
}

// Método para reescrever um advogado existente
public static void reescreverAdvogado(int numeroOAB, Advogado novoAdvogado, String path) {
    File file = new File(path, numeroOAB + ".txt");

    if (!file.exists()) {
        System.out.println("Advogado com Número OAB " + numeroOAB + " não encontrado.");
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("Número OAB: " + novoAdvogado.numeroOAB);
        writer.newLine();
        writer.write("Nome: " + novoAdvogado.nome);
        writer.newLine();
        writer.write("E-mail: " + novoAdvogado.email);
        writer.newLine();
        writer.write("Telefone: " + novoAdvogado.telefone);
        writer.newLine();
        writer.write("CPF ou CNPJ: " + novoAdvogado.cpfOuCnpj);
        System.out.println("Advogado atualizado com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao atualizar o advogado: " + e.getMessage());
    }
}
public static boolean verificarAdvogadoExiste(int numeroOAB, String path) {
    File dir = new File(path);
    File[] arquivos = dir.listFiles();

    if (arquivos != null) {
        for (File arquivo : arquivos) {
            List<String> linhas = null;
            try {
                linhas = Files.readAllLines(Paths.get(arquivo.getPath()));
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo: " + e.getMessage());
            }

            if (linhas != null) {
                for (String linha : linhas) {
                    if (linha.contains("Número OAB: " + numeroOAB)) {  // Verifica se o número OAB existe
                        return true;
                    }
                }
            }
        }
    }
    return false;
}

}
