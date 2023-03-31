import java.util.Scanner;

public class Main {

      public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       Arquivo arquivo = new Arquivo();
       String nomeArquivo = "";
       // Solicita o nome do arquivo de dados ao usuário
       int menu = -1;
       while(menu != 0){
         System.out.println("Escolha uma opção:");
         System.out.println("1 - Criar um novo arquivo");
         System.out.println("2 - Selecionar arquivo ");
         System.out.println("0 - prosseguir");
         menu = scanner.nextInt();
         scanner.nextLine(); // consome a quebra de linha

         if(menu == 1){
           arquivo.criarArquivo();
           nomeArquivo = arquivo.getNomeArquivo();

         }else if (menu == 2){
            arquivo.selecionarArquivo();
            nomeArquivo = arquivo.getNomeArquivo();
            
         }else if (menu == 0){
            // Cria o gerenciador de tarefas a partir do nome do arquivo fornecido
            GerenciadorTarefas gerenciador = new GerenciadorTarefas(nomeArquivo);
   
            int opcao = 1;
            while (opcao != 0) {
                // Apresenta as opções de operação disponíveis
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Criar nova tarefa");
                System.out.println("2 - Concluir tarefa");
                System.out.println("3 - Exibir tarefas pendentes");
                System.out.println("4 - Exibir tarefas concluídas");
                System.out.println("0 - Sair");
        
                // Lê a opção escolhida pelo usuário
                opcao = scanner.nextInt();
                scanner.nextLine(); // consome a quebra de linha
        
                if (opcao == 1) {
                    // Solicita o título e a descrição da tarefa ao usuário
                    
                    String titulo = Utils.lerTexto("Digite o título da tarefa:");
                    String descricao = Utils.lerTexto("Digite a descrição da tarefa:");
        
                    // Cria uma nova tarefa com os dados fornecidos e a adiciona ao gerenciador
                    Tarefa tarefa = new Tarefa(titulo, descricao);
                    gerenciador.adicionarTarefa(tarefa);
        
                    // Informa ao usuário que a tarefa foi adicionada com sucesso
                    Utils.imprimirTexto("Tarefa adicionada com sucesso!");
                } else if (opcao == 2) {
                   nomeArquivo = arquivo.getNomeArquivo();
                   Tarefa tarefaSelecionada = gerenciador.selecionarTarefa();
                   if (tarefaSelecionada != null) {
                       gerenciador.concluirTarefa(tarefaSelecionada);
                       System.out.println("Tarefa concluída com sucesso.");
                   } else {
                       System.out.println("Nenhuma tarefa selecionada.");
                   }
               }                     
                 else if (opcao == 3) {
                    // Exibe as tarefas pendentes ao usuário
                    gerenciador.exibirTarefasPendentes();
                } else if (opcao == 4) {
                    // Exibe as tarefas concluídas ao usuário
                    gerenciador.exibirTarefasConcluidas();
                } else if (opcao == 0) {
                    // Encerra o programa
                    Utils.imprimirTexto("Saindo...");
                } else {
                    // Informa ao usuario que a opção é invalida
                    Utils.imprimirTexto("Opção inválida.");
                }
              }
       
     }else{
     
     }
   }
  }  
} 

