package scr;

import java.io.IOException;
import java.util.Scanner;

public class SistemaProcessual {

    private static final String PATH_PROCESSOS = "Arquivos/processos/";
    private static final String PATH_CONTATOS = "Arquivos/contatos/";
    private static final String PATH_ADVOGADOS = "Arquivos/advogados/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gerenciar Contatos");
            System.out.println("2. Gerenciar Advogados");
            System.out.println("3. Gerenciar Processos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> menuContatos(scanner);
                case 2 -> menuAdvogados(scanner);
                case 3 -> menuProcessos(scanner);
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // MENU CONTATOS
    private static void menuContatos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU CONTATOS ---");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Listar Todos os Contatos");
            System.out.println("3. Buscar Contato");
            System.out.println("4. Remover Contato");
            System.out.println("5. Atualizar Contato");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> adicionarContato(scanner);
                case 2 -> FileManager.obterListaDeContatos(PATH_CONTATOS);  // Listar todos os contatos
                case 3 -> buscarContato(scanner);  // Buscar contato
                case 4 -> removerContato(scanner); // Remover contato
                case 5 -> atualizarContato(scanner); // Atualizar contato
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // MENU ADVOGADOS
    private static void menuAdvogados(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU ADVOGADOS ---");
            System.out.println("1. Adicionar Advogado");
            System.out.println("2. Listar Todos os Advogados");
            System.out.println("3. Buscar Advogado");
            System.out.println("4. Remover Advogado");
            System.out.println("5. Atualizar Advogado");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> adicionarAdvogado(scanner);
                case 2 -> FileManager.listarTodosAdvogados(PATH_ADVOGADOS);  // Listar todos os advogados
                case 3 -> buscarAdvogado(scanner); // Buscar advogado
                case 4 -> removerAdvogado(scanner); // Remover advogado
                case 5 -> atualizarAdvogado(scanner); // Atualizar advogado
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // MENU PROCESSOS
    private static void menuProcessos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU PROCESSOS ---");
            System.out.println("1. Adicionar Processo");
            System.out.println("2. Listar Todos os Processos");
            System.out.println("3. Buscar Processo");
            System.out.println("4. Remover Processo");
            System.out.println("5. Atualizar Processo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> adicionarProcesso(scanner);
                case 2 -> FileManager.listarTodosProcessos(PATH_PROCESSOS);  // Listar todos os processos
                case 3 -> buscarProcesso(scanner); // Buscar processo
                case 4 -> removerProcesso(scanner);
                case 5 -> atualizarProcesso(scanner); // Remover processo
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Adicionar Contato
    private static void adicionarContato(Scanner scanner) {
        // Lógica de adição de contato
        System.out.print("Digite o ID do Contato: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Digite o E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite o CPF ou CNPJ: ");
        String cpfCnpj = scanner.nextLine();

        Contato contato = new Contato(id, nome, dataNascimento, email, cpfCnpj);

        try {
            FileManager.gravarContato(contato, PATH_CONTATOS);
            System.out.println("Contato salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o contato: " + e.getMessage());
        }
    }

    // Adicionar Advogado
    private static void adicionarAdvogado(Scanner scanner) {
        // Lógica de adição de advogado
        System.out.print("Digite o Número OAB do Advogado: ");
        int numeroOAB = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o Nome do Advogado: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o E-mail do Advogado: ");
        String email = scanner.nextLine();

        System.out.print("Digite o Telefone do Advogado: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o CPF ou CNPJ do Advogado: ");
        String cpfCnpj = scanner.nextLine();

        Advogado advogado = new Advogado(numeroOAB, nome, email, telefone, cpfCnpj);

        try {
            FileManager.gravarAdvogado(advogado, PATH_ADVOGADOS);
            System.out.println("Advogado salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o advogado: " + e.getMessage());
        }
    }

    // Adicionar Processo
    private static void adicionarProcesso(Scanner scanner) {
        System.out.print("Digite o Número do Processo: ");
        int numeroProcesso = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
    
        System.out.print("Digite o Tipo de Ação: ");
        String tipoAcao = scanner.nextLine();
    
        System.out.print("Digite o ID do Autor: ");
        int autorId = scanner.nextInt();
        scanner.nextLine();
        
        if (!FileManager.verificarContatoExiste(autorId, PATH_CONTATOS)) {
            System.out.println("Erro: O ID do autor não existe.");
            return;
        }
    
        System.out.print("Digite o ID do Réu: ");
        int reu = scanner.nextInt();
        scanner.nextLine();
        
        if (!FileManager.verificarContatoExiste(reu, PATH_CONTATOS)) {
            System.out.println("Erro: O ID do réu não existe.");
            return;
        }
    
        System.out.print("Digite o ID do Advogado: ");
        int advogadoId = scanner.nextInt();
        scanner.nextLine();
    
        if (!FileManager.verificarAdvogadoExiste(advogadoId, PATH_ADVOGADOS)) {
            System.out.println("Erro: O ID do advogado não existe.");
            return;
        }
    
        Processo processo = new Processo(numeroProcesso, tipoAcao, autorId, reu, advogadoId);
    
        try {
            FileManager.gravarProcesso(processo, PATH_PROCESSOS, tipoAcao);
            System.out.println("Processo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o processo: " + e.getMessage());
        }
    }
    // Buscar Contato
    private static void buscarContato(Scanner scanner) {
        // Lógica de busca de contato
        System.out.print("Digite o ID do Contato que deseja buscar: ");
        int idContato = scanner.nextInt();

        FileManager.buscarContato(idContato, PATH_CONTATOS);  // Método para buscar e listar os contatos
    }

    // Buscar Advogado
    private static void buscarAdvogado(Scanner scanner) {
        // Lógica de busca de advogado
        System.out.print("Digite o Número OAB do Advogado que deseja buscar: ");
        int numeroOAB = scanner.nextInt();

        FileManager.buscarAdvogado(numeroOAB, PATH_ADVOGADOS);  // Método para buscar e listar os advogados
    }

    // Buscar Processo
    private static void buscarProcesso(Scanner scanner) {
        // Lógica de busca de processo
        System.out.print("Digite o Número do Processo que deseja buscar: ");
        int numeroProcesso = scanner.nextInt();

        FileManager.buscarProcesso(numeroProcesso, PATH_PROCESSOS);  // Método para buscar e listar os processos
    }

    // Adicionar opção para remover contato
private static void removerContato(Scanner scanner) {
    System.out.print("Digite o ID do Contato que deseja remover: ");
    int idContato = scanner.nextInt();
    FileManager.removerContato(idContato, PATH_CONTATOS);
}

// Adicionar opção para remover advogado
private static void removerAdvogado(Scanner scanner) {
    System.out.print("Digite o Número OAB do Advogado que deseja remover: ");
    int numeroOAB = scanner.nextInt();
    FileManager.removerAdvogado(numeroOAB, PATH_ADVOGADOS);
}

// Adicionar opção para remover processo
private static void removerProcesso(Scanner scanner) {
    System.out.print("Digite o Número do Processo que deseja remover: ");
    int numeroProcesso = scanner.nextInt();
    FileManager.removerProcesso(numeroProcesso, PATH_PROCESSOS);
}

// Método para atualizar contato
private static void atualizarContato(Scanner scanner) {
    System.out.print("Digite o ID do Contato que deseja atualizar: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consumir nova linha

    System.out.print("Digite o Novo Nome: ");
    String nome = scanner.nextLine();

    System.out.print("Digite a Nova Data de Nascimento (dd/mm/aaaa): ");
    String dataNascimento = scanner.nextLine();

    System.out.print("Digite o Novo E-mail: ");
    String email = scanner.nextLine();

    System.out.print("Digite o Novo CPF ou CNPJ: ");
    String cpfCnpj = scanner.nextLine();

    Contato novoContato = new Contato(id, nome, dataNascimento, email, cpfCnpj);
    FileManager.reescreverContato(id, novoContato, PATH_CONTATOS);
}

// Método para atualizar advogado
private static void atualizarAdvogado(Scanner scanner) {
    System.out.print("Digite o Número OAB do Advogado que deseja atualizar: ");
    int numeroOAB = scanner.nextInt();
    scanner.nextLine(); // Consumir nova linha

    System.out.print("Digite o Novo Nome: ");
    String nome = scanner.nextLine();

    System.out.print("Digite o Novo E-mail: ");
    String email = scanner.nextLine();

    System.out.print("Digite o Novo Telefone: ");
    String telefone = scanner.nextLine();

    System.out.print("Digite o Novo CPF ou CNPJ: ");
    String cpfCnpj = scanner.nextLine();

    Advogado novoAdvogado = new Advogado(numeroOAB, nome, email, telefone, cpfCnpj);
    FileManager.reescreverAdvogado(numeroOAB, novoAdvogado, PATH_ADVOGADOS);
}

// Método para atualizar processo
private static void atualizarProcesso(Scanner scanner) {
    System.out.print("Digite o Número do Processo que deseja atualizar: ");
    int numeroProcesso = scanner.nextInt();
    scanner.nextLine(); // Consumir a nova linha

    System.out.print("Digite o Novo Tipo de Ação: ");
    String tipoAcao = scanner.nextLine();

    System.out.print("Digite o Novo ID do Autor: ");
    int autorId = scanner.nextInt();
    scanner.nextLine();

    // Verificar se o ID do autor existe
    if (!FileManager.verificarContatoExiste(autorId, PATH_CONTATOS)) {
        System.out.println("Erro: O ID do autor não existe.");
        return;
    }

    System.out.print("Digite o Novo ID do Réu: ");
    int reu = scanner.nextInt();

    // Verificar se o ID do réu existe
    if (!FileManager.verificarContatoExiste(reu, PATH_CONTATOS)) {
        System.out.println("Erro: O ID do réu não existe.");
        return;
    }

    System.out.print("Digite o Novo ID do Advogado: ");
    int advogadoId = scanner.nextInt();
    scanner.nextLine();

    // Verificar se o ID do advogado existe
    if (!FileManager.verificarAdvogadoExiste(advogadoId, PATH_ADVOGADOS)) {
        System.out.println("Erro: O ID do advogado não existe.");
        return;
    }

    // Atualizar o processo com os novos dados
    Processo novoProcesso = new Processo(numeroProcesso, tipoAcao, autorId, reu, advogadoId);
    
    FileManager.reescreverProcesso(numeroProcesso, novoProcesso, PATH_PROCESSOS);
    System.out.println("Processo atualizado com sucesso!");
}


}
