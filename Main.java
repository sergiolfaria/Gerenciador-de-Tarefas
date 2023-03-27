import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o nome do arquivo de dados ao usuário
        System.out.print("Digite o nome do arquivo de dados: ");
        String nomeArquivo = scanner.nextLine();

        // Cria o gerenciador de tarefas a partir do nome do arquivo fornecido
        GerenciadorTarefas gerenciador = new GerenciadorTarefas(nomeArquivo);

        // Cria um scanner para a leitura das opções do usuário
        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        do {
            // Apresenta as opções de operação disponíveis
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar nova tarefa");
            System.out.println("2 - Concluir tarefa");
            System.out.println("3 - Exibir tarefas pendentes");
            System.out.println("4 - Exibir tarefas concluídas");
            System.out.println("0 - Sair");

            // Lê a opção escolhida pelo usuário
            opcao = sc.nextInt();
            sc.nextLine(); // consome a quebra de linha

            switch (opcao) {
                case 1:
                    // Solicita o título e a descrição da tarefa ao usuário
                    System.out.println("Digite o título da tarefa:");
                    String titulo = sc.nextLine();
                    System.out.println("Digite a descrição da tarefa:");
                    String descricao = sc.nextLine();

                    // Cria uma nova tarefa com os dados fornecidos e a adiciona ao gerenciador
                    Tarefa tarefa = new Tarefa(titulo, descricao);
                    gerenciador.adicionarTarefa(tarefa);

                    // Informa ao usuário que a tarefa foi adicionada com sucesso
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case 2:
                    // Solicita o título da tarefa a ser concluída ao usuário
                    System.out.println("Digite o título da tarefa a ser concluída:");
                    String tituloConcluir = sc.nextLine();

                    // Busca a tarefa com o título fornecido no gerenciador de tarefas
                    Tarefa tarefaAConcluir = gerenciador.buscarTarefaPorTitulo(tituloConcluir);
                    if (tarefaAConcluir != null) {
                        // Se a tarefa foi encontrada, a conclui e informa ao usuário
                        gerenciador.concluirTarefa(tarefaAConcluir);
                        System.out.println("Tarefa concluída com sucesso!");
                    } else {
                        // Se a tarefa não foi encontrada, informa ao usuário
                        System.out.println("Tarefa não encontrada.");
                    }
                    break;
                case 3:
                    // Exibe as tarefas pendentes ao usuário
                    gerenciador.exibirTarefasPendentes();
                    break;
                case 4:
                    // Exibe as tarefas concluídas ao usuário
                    gerenciador.exibirTarefasConcluidas();
                    break;
                case 0:
                    // Encerra o programa
                    System.out.println("Saindo...");
                    break;
                default:
                    // Informa ao usuario que a opção é invalida

                    System.out.println("Opção inválida.");
                    break;
            }
        //Encerra a repetição do programa
        } while (opcao != 0);
        
        sc.close();
    }

}
