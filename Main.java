import java.util.Scanner;

public class Main {
    private static GerenciadorTarefas gerenciador;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        gerenciador = new GerenciadorTarefas();
        scanner = new Scanner(System.in);
        
        int opcao = 0;
        while (opcao != 5) {
            Utils.imprimirTexto("=== Gerenciador de Tarefas ===");
            Utils.imprimirTexto("1 - Criar nova tarefa");
            Utils.imprimirTexto("2 - Concluir tarefa");
            Utils.imprimirTexto("3 - Exibir tarefas pendentes");
            Utils.imprimirTexto("4 - Exibir tarefas concluídas");
            Utils.imprimirTexto("5 - Sair");
            
            Utils.imprimirTexto("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir a quebra de linha pendente
            
            switch (opcao) {
                case 1:
                    Utils.imprimirTexto("\nTítulo: ");
                    String titulo = scanner.nextLine();
                    
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    
                    Tarefa tarefa = new Tarefa(titulo, descricao);
                    gerenciador.adicionarTarefa(tarefa);
                    System.out.println("\nTarefa criada com sucesso!");
                    break;
                    
                case 2:
                    System.out.print("\nID da tarefa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consumir a quebra de linha pendente
                    
                    Tarefa t = gerenciador.getTarefa(id);
                    if (t == null) {
                        System.out.println("\nTarefa não encontrada!");
                    } else {
                        gerenciador.concluirTarefa(t);
                        System.out.println("\nTarefa concluída com sucesso!");
                    }
                    break;
                    
                case 3:
                    System.out.println("\n=== Tarefas pendentes ===");
                    for (Tarefa pendente : gerenciador.listarTarefasPendentes()) {
                        System.out.println(pendente);
                    }
                    break;
                    
                case 4:
                    System.out.println("\n=== Tarefas concluídas ===");
                    for (Tarefa concluida : gerenciador.listarTarefasConcluidas()) {
                        System.out.println(concluida);
                    }
                    break;
                    
                case 5:
                    System.out.println("\nSaindo...");
                    break;
                    
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        }
        scanner.close();
    }
}
