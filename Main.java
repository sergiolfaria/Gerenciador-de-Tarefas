import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
   
   static String status ="pendente";
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        //int opcao = -1;
        Arquivo arquivo = new Arquivo();
;
        int opcao = 0;
        while (opcao != 3) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar arquivo");
            System.out.println("2 - Ver arquivo");
            System.out.println("3 - Prosseguir");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer do scanner

            if (opcao == 1) {
                arquivo.criarArquivo();
            } else if (opcao == 2) {
                System.out.print("Digite o nome do arquivo: ");
                String nome = scanner.nextLine();
                arquivo.verArquivo(nome);
            } else if (opcao == 3) {
                  while (opcao != 0) {
                  Utils.imprimirTexto("\n==== Menu ====\n");
                  Utils.imprimirTexto("1 - Criar nova tarefa\n");
                  Utils.imprimirTexto("2 - Concluir tarefa\n");
                  Utils.imprimirTexto("3 - Exibir tarefas pendentes\n");
                  Utils.imprimirTexto("4 - Exibir tarefas concluídas\n");
                  Utils.imprimirTexto("0 - Sair\n");
                  opcao = Utils.lerInt("Digite a opção desejada: \n");
           
               if (opcao == 1) {
                   String titulo = Utils.lerTexto("Digite o título da tarefa: ");
                   String descricao = Utils.lerTexto("Digite a descrição da tarefa: ");
                   Date dataInicio = Utils.lerData("Digite a data de início da tarefa: ");
                   Tarefa tarefa = GerenciadorTarefas.criarTarefa(titulo, descricao);
                   System.out.println("Tarefa criada: " + tarefa);
               } else if (opcao == 2) {             
                   int id = Utils.lerInt("Digite o ID da tarefa que deseja concluir: ");        
                   gerenciador.concluirTarefa( );
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
       }else {
           System.out.println("Opção inválida."); 

            
            } 
            }
        }
    }

