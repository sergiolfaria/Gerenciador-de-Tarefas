import java.util.Scanner;

public class Main {
   
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("==== Menu ====");
            System.out.println("1 - Criar nova tarefa");
            System.out.println("2 - Concluir tarefa");
            System.out.println("3 - Exibir tarefas pendentes");
            System.out.println("4 - Exibir tarefas concluídas");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
               
                System.out.print("Digite o título da tarefa: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a descrição da tarefa: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite a data de início da tarefa (dd/mm/aaaa): ");
                String dataInicio = scanner.nextLine();
                Tarefa tarefa = gerenciador.criarTarefa(titulo, descricao, dataInicio);
                System.out.println("Tarefa criada: " + tarefa);
            } else if (opcao == 2) {
               
                System.out.print("Digite o ID da tarefa que deseja concluir: ");
                int id = scanner.nextInt();
                scanner.nextLine(); 
                gerenciador.concluirTarefa(id);
                System.out.println("Tarefa concluída com sucesso!");
            } else if (opcao == 3) {
               
                System.out.println("Tarefas pendentes:");
                for (Tarefa t : gerenciador.listarTarefasPendentes()) {
                    System.out.println(t);
                }
            } else if (opcao == 4) {
             
                System.out.println("Tarefas concluídas:");
                for (Tarefa t : gerenciador.listarTarefasConcluidas()) {
                    System.out.println(t);
                }
            } else if (opcao == 0) {
           
                System.out.println("Saindo do sistema...");
            } else {
                System.out.println("Opção inválida!");
            }
            System.out.println(); 
        }

        scanner.close();
    }
}
